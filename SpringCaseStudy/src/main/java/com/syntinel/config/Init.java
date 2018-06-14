package com.syntinel.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Init extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
    protected Class<?>[] getRootConfigClasses() {
      return new Class[] {Config.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
       return null;
    }

    @Override
    protected String[] getServletMappings() {
       return new String[] {"/"};
    }
}
