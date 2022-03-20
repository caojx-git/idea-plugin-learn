package com.caojx.learn.idea.plugin.markbook.processor;

public interface Processor {

    /**
     * 处理文档
     */
    void process(SourceNoteData sourceNoteData) throws Exception;
}
