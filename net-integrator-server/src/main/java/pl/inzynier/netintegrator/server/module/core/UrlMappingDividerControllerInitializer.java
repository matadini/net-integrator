package pl.inzynier.netintegrator.server.module.core;

import lombok.Value;
import pl.inzynier.netintegrator.server.module.urlmapping.UrlMappingService;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.server.shared.UrlMappingConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Komponent pobierajacy konfiguracje z bazy i wystawiajacy mapowania na
 * targetowym kontrolerze {@link UrlMappingDividerController}
 */
@Value
@Component
class UrlMappingDividerControllerInitializer {

	UrlMappingService urlMappingRepository;
	UrlMappingDividerController dividerController;
	RequestMappingHandlerMapping handlerMapping;

	@Autowired
	public UrlMappingDividerControllerInitializer(RequestMappingHandlerMapping handlerMapping,
			UrlMappingService urlMappingRepository, UrlMappingDividerController dividerController) {

		this.handlerMapping = handlerMapping;
		this.urlMappingRepository = urlMappingRepository;
		this.dividerController = dividerController;
	}

	/*
	 * Wystaw endpointy dynamicznie skonfigurowane w bazie
	 */
	@PostConstruct
	void initAdd() {

		try {

			urlMappingRepository.demoInit();

			Class[] cArg = new Class[2];
			cArg[0] = HttpServletRequest.class;
			cArg[1] = HttpServletResponse.class;

			/*
			 * Pobierz skonfigurowane endpointy
			 */
			List<UrlMappingDto> mappingList = this.urlMappingRepository.findAll();
			for (UrlMappingDto mapping : mappingList) {

				PublishEndpointDto publishEndpoint = mapping.getEndpoint();

				RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths(publishEndpoint.getMethodUrl())
						.methods().build();

				Method handler = UrlMappingDividerController.class
						.getDeclaredMethod(UrlMappingConst.DIVIDER_CONTROLLER_HANDLE_METHOD_NAME, cArg);
				handlerMapping.registerMapping(requestMappingInfo, dividerController, handler);
			}

		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
