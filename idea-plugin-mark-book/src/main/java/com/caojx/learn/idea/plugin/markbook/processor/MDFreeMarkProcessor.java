package com.caojx.learn.idea.plugin.markbook.processor;

import com.intellij.ide.fileTemplates.impl.UrlUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;

public class MDFreeMarkProcessor extends AbstractFreeMarkProcessor {
    @Override
    protected Template getTemplate() throws IOException, Exception {

        //创建模板配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        //创建字符串模板的导入器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        //导入字符串模板

        //加载模板字符串
        String templateString = UrlUtil.loadText(MDFreeMarkProcessor.class.getResource("/template/md.ftl"));
        stringTemplateLoader.putTemplate("MDTemplate", templateString);
        configuration.setTemplateLoader(stringTemplateLoader);
        //获取模板
        return configuration.getTemplate("MDTemplate");
    }

    @Override
    protected Object getModel(SourceNoteData sourceNoteData) {
        HashMap model = new HashMap();
        model.put("topic",sourceNoteData.getTopic());
        model.put("noteList",sourceNoteData.getNoteList());
        return model;
    }

    @Override
    protected Writer getWriter(SourceNoteData sourceNoteData) throws FileNotFoundException, Exception {
        String filePath = sourceNoteData.getFileName();
        File file = new File(filePath);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
    }
}
