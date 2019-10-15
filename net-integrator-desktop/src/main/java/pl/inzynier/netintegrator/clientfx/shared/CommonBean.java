package pl.inzynier.netintegrator.clientfx.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class CommonBean {

    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }

}
