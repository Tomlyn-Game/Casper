package moe.protasis.casper.config;

import jakarta.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
@Slf4j
public class FileUploadConfig {
    @Autowired
    private JsonWrapper jsonConfig;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        var size = DataSize.parse(jsonConfig.GetString("packageSizeLimit", "1GB"));
        factory.setMaxFileSize(size);
        factory.setMaxRequestSize(size);

        log.info("File upload size limit set to {}MB.", size.toMegabytes());

        return factory.createMultipartConfig();
    }
}
