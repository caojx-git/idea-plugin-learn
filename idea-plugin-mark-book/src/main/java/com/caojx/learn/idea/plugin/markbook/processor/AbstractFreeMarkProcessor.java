package com.caojx.learn.idea.plugin.markbook.processor;


import freemarker.template.Template;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractFreeMarkProcessor implements Processor {

    /**
     * 具体的模板
     * @return
     * @throws IOException
     * @throws Exception
     */
    protected abstract Template getTemplate() throws IOException, Exception;

    /**
     * 获取数据
     * @param sourceNoteData
     * @return
     */
    protected abstract Object getModel(SourceNoteData sourceNoteData);

    /**
     * 文件写到哪里
     * @param sourceNoteData
     * @return
     * @throws FileNotFoundException
     * @throws Exception
     */
    protected abstract Writer getWriter(SourceNoteData sourceNoteData) throws FileNotFoundException, Exception;



    @Override
    public void process(SourceNoteData sourceNoteData) throws Exception {
        Template template = getTemplate();
        Object model = getModel(sourceNoteData);
        Writer writer = getWriter(sourceNoteData);
        template.process(model,writer);
    }
}
