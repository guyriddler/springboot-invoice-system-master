package com.rt.springboot.app;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = "/actuator/*")
public class SkipAccessLogConfig implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            String path = ((HttpServletRequest) request).getRequestURI();

            // Skip logging for prometheus, metrics, or health endpoints
            if (path.matches(".*/actuator/(prometheus|metrics|health).*")) {
                request.setAttribute("skipAccLog", Boolean.TRUE);
            }

            chain.doFilter(request, response);
        }
    }


