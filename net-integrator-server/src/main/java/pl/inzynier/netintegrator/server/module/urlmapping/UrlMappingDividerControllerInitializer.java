package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

@Value
@Component
class UrlMappingDividerControllerInitializer {

    RequestMappingHandlerMapping handlerMapping;

    UrlMappingDividerController dividerController;

    @Autowired
    public UrlMappingDividerControllerInitializer(RequestMappingHandlerMapping handlerMapping, UrlMappingDividerController dividerController) {
        this.handlerMapping = handlerMapping;
        this.dividerController = dividerController;
    }

    /*
     * Wystaw endpointy dynamicznie skonfigurowane w bazie
     */
    @PostConstruct
    void initAdd() {

        try {

            /*
             * Pobierz skonfigurowane endpointy
             */



            /*
             * Utworz manifest endpointu do wystawienia na podstawie konfiguracji z bazy
             */
            RequestMappingInfo requestMappingInfo = RequestMappingInfo
                    .paths("/dodany")
                    .methods(RequestMethod.GET)
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .build();

            /*
             * Wystaw endpoint na kontrolerze rozdzielczym
             */
            System.err.println(Arrays.toString(UrlMappingDividerController.class.getMethods()));
            System.err.println(Arrays.toString(UrlMappingDividerController.class.getDeclaredMethods()));

            Class[] cArg = new Class[2];
            cArg[0] = HttpServletRequest.class;
            cArg[1] =  HttpServletResponse.class;

            Method handler = UrlMappingDividerController.class.getDeclaredMethod(UrlMappingConst.DIVIDER_CONTROLLER_HANDLE_METHOD_NAME,cArg);
            handlerMapping.registerMapping(requestMappingInfo, dividerController, handler);

        }catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
