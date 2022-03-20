package com.caojx.learn.idea.plugin.markbook.processor;

import com.caojx.learn.idea.plugin.markbook.data.OneNoteData;

import java.util.List;

public interface SourceNoteData {

    /**
     * 获取文件名称
     * @return
     */
    String getFileName();

    /**
     * 获取笔记辩题
     * @return
     */
    String getTopic();

    /**
     * 获取笔记列表
     * @return
     */
    List<OneNoteData> getNoteList();


}
