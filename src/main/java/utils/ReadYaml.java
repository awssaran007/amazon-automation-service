package utils;


import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class ReadYaml {
    Yaml yaml = new Yaml();
    String resourcesPath  = "src\\test\\resources\\test-data\\";
    String fileName;

    public ReadYaml(String env) {
        if (env.toUpperCase().equals("QA"))
            fileName = "QA_Config.yaml";
        else if (env.toUpperCase().equals("DEV"))
            fileName = "DEV_Config.yaml";
    }

    public Map<String, String> readConfig() throws FileNotFoundException {
        Map<String, String> dict = yaml.load(new FileReader(new File(resourcesPath + fileName)));
        return dict;
    }

}
