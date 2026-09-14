package io.bootify.my_app.config;

import gg.jte.springframework.boot.autoconfigure.JteViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.Ordered;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@ControllerAdvice
public class ControllerConfig {

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    public void jteViewResolverOrder(final JteViewResolver jteViewResolver) {
        jteViewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
