package pl.inzynier.netintegrator.server.configuration;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.File;

@RequiredArgsConstructor
class ConfigurationRepositoryJsonImpl implements ConfigurationRepository {

    private final Gson gson;
    private final String configFilePath;



    @Override
    public boolean createOrUpdate(Configuration entity) {

        boolean toReturn = true;
        try {

            String string = gson.toJson(entity);
            com.google.common.io.Files.write(string, new File(configFilePath), Charsets.UTF_8);
        } catch (Exception ex) {
            org.pmw.tinylog.Logger.info(ex);
            toReturn = false;
        }
        return toReturn;
    }

    @Override
    public Configuration read() {
        Configuration toReturn = null;
        try {
            File file = new File(configFilePath);
            if (!file.exists()) {
                throw new Exception("file doesnt exist");
            }

            String string = Files.toString(file, Charsets.UTF_8);
            if (Strings.isNullOrEmpty(string)) {
                throw new Exception("file content is empty");
            }


            toReturn = gson.fromJson(string, Configuration.class);

        } catch (Exception ex) {
            org.pmw.tinylog.Logger.info(ex);
        }
        return toReturn;
    }

}
