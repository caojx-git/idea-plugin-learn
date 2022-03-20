package com.caojx.learn.idea.plugin.domain.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

public class FreemarkerConfiguration extends Configuration {

    public FreemarkerConfiguration() {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        setDefaultEncoding("UTF-8");
        setClassForTemplateLoading(getClass(), "/template");
    }

    public Template getTemplate(String ftl) throws IOException {
        return this.getTemplate(ftl, "UTF-8");
    }
}
