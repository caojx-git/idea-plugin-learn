package com.caojx.learn.idea.plugin.infrastructure.po;

import java.util.List;

public class Table {

    /**
     * 表注释
     */
    private String comment;

    /**
     * 表名
     */
    private String name;

    /**
     * 列名
     */
    private List<Column> columns;

    public Table(String comment, String name, List<Column> columns) {
        this.comment = comment;
        this.name = name;
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
