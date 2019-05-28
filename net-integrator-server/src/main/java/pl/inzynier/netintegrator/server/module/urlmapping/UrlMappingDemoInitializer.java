package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import javax.annotation.PostConstruct;

/**
 * Inicjalizuje testowe dane w tabeli konfiguracji mapowan
 */
@Component
class UrlMappingDemoInitializer {

    private final UrlMappingRepository mappingRepository;

    private final ScriptService scriptService;

    @Autowired
    public UrlMappingDemoInitializer(UrlMappingRepository mappingRepository, ScriptService scriptService) {
        this.mappingRepository = mappingRepository;
        this.scriptService = scriptService;
    }

    private String createExampleGroovyScript() {
        return "//imports\n" +
                "import groovy.json.JsonOutput\n" +
                "import groovy.json.JsonSlurper\n" +
                "\n" +
                "//define input data class\n" +
                "class Person {\n" +
                "    String name\n" +
                "    String surname\n" +
                "}\n" +
                "\n" +
                "// Read input data\n" +
                "def input = args[0]\n" +
                "\n" +
                "// convert to UDR\n" +
                "def slurper = new JsonSlurper()\n" +
                "def personMap = slurper.parseText(input)\n" +
                "def newPerson = new Person(personMap)\n" +
                "\n" +
                "// transform data\n" +
                "newPerson.name = newPerson.name.toUpperCase()\n" +
                "newPerson.surname = newPerson.surname.toUpperCase()\n" +
                "\n" +
                "// return data\n" +
                "return JsonOutput.toJson(newPerson)\n";
    }

    @PostConstruct
    private void initializeDemo() throws ScriptServiceException {

        // 0
        PublishEndpoint publishEndpoint0 = new PublishEndpoint(
                "/api-get",
                RequestMethod.GET);

        TargetEndpoint targetEndpoint0 = new TargetEndpoint(
                "/fake-get",
                "http://localhost:9090",
                RequestMethod.GET);

        UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
        mappingRepository.save(mapping0);

        // 2
        PublishEndpoint publishEndpoint2 = new PublishEndpoint(
                "/api-post",
                RequestMethod.POST);

        TargetEndpoint targetEndpoint2 = new TargetEndpoint(
                "/fake-post",
                "http://localhost:9090",
                RequestMethod.POST);

        UrlMapping mapping2 = new UrlMapping(publishEndpoint2, targetEndpoint2);
        UrlMapping save = mappingRepository.save(mapping2);
        scriptService.addScript(save.getUrlMappingId(), ScriptType.POST_CALL, createExampleGroovyScript());
        // przetestuj kontrolnie

    }
}
