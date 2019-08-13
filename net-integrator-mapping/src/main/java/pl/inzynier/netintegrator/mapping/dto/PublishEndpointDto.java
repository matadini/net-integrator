package pl.inzynier.netintegrator.mapping.dto;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishEndpointDto {

    String methodUrl;
    RequestMethod method;
}
