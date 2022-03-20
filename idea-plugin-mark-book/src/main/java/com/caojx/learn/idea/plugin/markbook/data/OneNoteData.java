package com.caojx.learn.idea.plugin.markbook.data;

/**
 * 确定一条笔记需要的字段创建NoteData类
 */
public class OneNoteData {

    /**
     * 笔记标题
     */
    private String title;
    /**
     * 笔记内容
     */
    private String mark;

    /**
     * 标记的源码
     */
    private String sourceCode;
    /**
     * 源码所在的文件名
     */
    private String fileName;
    /**
     * 源码所在的文件类型
     */
    private String fileType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
