package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.util.JsonUtil;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.File;

@Configuration
@Slf4j
public class JsonConfigService {
    @Value("classpath:config.json")
    private Resource configTemplate;
    @Autowired
    private ConfigurableEnvironment env;

    private JsonWrapper config = new JsonWrapper();

    @PostConstruct
    private void Init() {
        // Load the config
        log.info("Loading config...");
        File file = new File("config.json").getAbsoluteFile();
        log.info("Config file at: {}", file.getAbsolutePath());

        try {
            config = JsonUtil.UpdateAndWrite(file, configTemplate.getInputStream());
        } catch (Exception e) {
            log.error("Failed to load config!");
            throw new RuntimeException(e);
        }

        log.info("JSON Config loaded.");
    }

    @Bean
    public JsonWrapper ServerConfig() {
        return config;
    }
}
