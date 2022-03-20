package com.caojx.learn.idea.plugin.markbook.processor;

import com.caojx.learn.idea.plugin.markbook.data.OneNoteData;

import java.util.List;

public class DefaultSourceNoteData implements SourceNoteData {

    private String fileName;

    private String topic;

    private List<OneNoteData> noteList;

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public List<OneNoteData> getNoteList() {
        return noteList;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setNoteList(List<OneNoteData> noteList) {
        this.noteList = noteList;
    }
}
