package moe.protasis.casper.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@UtilityClass
@Slf4j
public class JsonUtil {
    public static JsonWrapper Read(File file) {
        if (!file.exists() || file.length() == 0) return new JsonWrapper();

        try (FileReader reader = new FileReader(file)) {
            return new JsonWrapper(new ObjectMapper().readTree(reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonWrapper UpdateAndWrite(File file, InputStream res) {
        try {
            return UpdateAndWrite(file, new JsonWrapper(new ObjectMapper().readTree(Util.ReadToStringAutoClose(res))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonWrapper UpdateAndWrite(File file, JsonWrapper def) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                Files.createFile(file.toPath());
            }

            JsonWrapper current = Read(file);
            UpdateJson(current, def);
            current.Save(file);
            return current;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UpdateJson(JsonWrapper current, JsonWrapper def) {
        for (String key : def.GetKeys()) {
            JsonNode ele = current.Get(key);
            JsonNode defEle = def.Get(key);

            if (ele == null) current.Set(key, defEle);
            else if (defEle.isObject() && ele.isObject())
                UpdateJson(new JsonWrapper(ele), new JsonWrapper(defEle));
        }
    }

}