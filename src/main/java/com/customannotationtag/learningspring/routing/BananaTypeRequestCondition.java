package com.customannotationtag.learningspring.routing;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class BananaTypeRequestCondition implements RequestCondition<BananaTypeRequestCondition> {
    private String type="";
    private String pathPattern ="";

    private final AntPathMatcher antPathMatcher = new AntPathMatcher("/");

    public BananaTypeRequestCondition(String type, String pathPattern) {
        this.type = type;
        this.pathPattern = pathPattern + "/**";
    }

    @Override
    public BananaTypeRequestCondition combine(BananaTypeRequestCondition other) {
        return null;
    }

    @Override
    public BananaTypeRequestCondition getMatchingCondition(HttpServletRequest request) {
        var path = request.getRequestURI();
        // Extract variables from path and compare against the expected keyType
        var variables = antPathMatcher.extractUriTemplateVariables(pathPattern, path);
        // When we are sure that our path matches with the path from the request, then do something.
        if (antPathMatcher.matchStart(pathPattern, path) && variables.get("type").contains(type)) {
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(BananaTypeRequestCondition other, HttpServletRequest request) {
        return 0;
    }
}
