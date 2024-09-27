package moe.protasis.casper.config;

import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.Casper;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.resolver.JsonWrapperReturnResolver;
import moe.protasis.casper.resolver.PackageRepoTokenResolver;
import moe.protasis.casper.resolver.SimpleTypeResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PackageRepoTokenResolver());

        resolvers.add(new SimpleTypeResolver<>(ITomlynAPI.class, () -> Casper.getInstance().getServerApi()));
        resolvers.add(new SimpleTypeResolver<>(IPackageProvider.class, () -> Casper.getInstance().getPackageProvider()));
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new JsonWrapperReturnResolver());
    }

}
