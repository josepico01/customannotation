package com.customannotationtag.learningspring.routing;

import com.customannotationtag.learningspring.annotation.BananaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class DemoRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        BananaType typeAnnotation = AnnotationUtils.findAnnotation(handlerType, BananaType.class);
        return (typeAnnotation != null) ? new BananaTypeRequestCondition(typeAnnotation.type(), typeAnnotation.path()) : null;
    }
}
