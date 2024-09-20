package moe.protasis.tomlyn.config;

import moe.protasis.tomlyn.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Autowired
    private JsonWrapper jsonConfig;

    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(jsonConfig.GetInt("port", 7940));
    }
}
