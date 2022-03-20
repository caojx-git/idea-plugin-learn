package com.caojx.learn.idea.plugin.domain.service;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author: 小傅哥，微信：fustack
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class GeneratorConfig extends Configuration {

    public GeneratorConfig() {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        setDefaultEncoding("UTF-8");
        setClassForTemplateLoading(getClass(), "/template");
    }

    public Template getTemplate(String ftl) throws IOException {
        return this.getTemplate(ftl, "UTF-8");
    }

}
