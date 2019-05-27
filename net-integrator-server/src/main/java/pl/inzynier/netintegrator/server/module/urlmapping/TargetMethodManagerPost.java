package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.python.util.PythonInterpreter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptDto;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class TargetMethodManagerPost implements TargetMethodManager {

    RestTemplate restTemplate;

    ScriptService scriptService;

    @Override
    public ResponseEntity<String> manage(UrlMapping urlMapping, HttpServletRequest request) {

        try {
            TargetEndpoint target1 = urlMapping.getTarget();
            String fullUrl = target1.getFullUrl();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            // pobierz skrypty powiazany z mapowaniem
            List<ScriptDto> byUrlMappingId = scriptService.findByUrlMappingId(urlMapping.getUrlMappingId());
            for (ScriptDto script : byUrlMappingId) {

                String content = script.getContent();

                // wykonaj skrypt
                StringWriter outStream = new StringWriter();
                PythonInterpreter pi = new PythonInterpreter();
                pi.setOut(outStream);
                pi.exec(bodyAsPythonSysArgv(body));
                pi.exec(content);

                body = outStream.toString().replaceAll("\\s+","");
            }
            return restTemplate.postForEntity(fullUrl, body, String.class);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private String bodyAsPythonSysArgv(String body) {
        return "import sys\n"+"sys.argv = ['', '"+body+"']";
    }
}
