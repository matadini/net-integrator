package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Value;
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

    private String productExamplePythonScript() {
        return "import sys\n" +
                "import json\n" +
                "\n" +
                "# example call: py test.py '{ \"name\":\"Janusz\", \"surname\":\"Nosacz\"}'\n" +
                "\n" +
                "# define input data structure\n" +
                "class Person:\n" +
                "    def __init__(self, name, surname):\n" +
                "        self.name = name\n" +
                "        self.surname = surname\n" +
                "\n" +
                "    def toJSON(self):\n" +
                "        return json.dumps(self, default=lambda o: o.__dict__, \n" +
                "            sort_keys=True, indent=4)\n" +
                "# define ouput data structure\n" +
                "\n" +
                "\n" +
                "# read input args\n" +
                "arg = sys.argv[1]\n" +
                "loads = json.loads(arg)\n" +
                "object = Person(**loads)\n" +
                "\n" +
                "# transform data\n" +
                "object.name = object.name.upper()\n" +
                "object.surname = object.surname.upper()\n" +
                "\n" +
                "# return data\n" +
                "\n" +
                "print(object.toJSON())";
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
        scriptService.addScript(save.getUrlMappingId(), ScriptType.POST_CALL, productExamplePythonScript());
        // przetestuj kontrolnie

    }
}
