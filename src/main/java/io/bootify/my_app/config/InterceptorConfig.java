package io.bootify.my_app.config;

import io.bootify.my_app.util.JteContextInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private final JteContextInterceptor jteContextInterceptor;

    public InterceptorConfig(final JteContextInterceptor jteContextInterceptor) {
        this.jteContextInterceptor = jteContextInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(jteContextInterceptor);
    }

}
