package pl.inzynier.netintegrator.server.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.python.util.PythonInterpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class BeanConfiguration {

    @Bean
    Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PythonInterpreter pythonInterpreter() {
        return new PythonInterpreter();
    }
}
