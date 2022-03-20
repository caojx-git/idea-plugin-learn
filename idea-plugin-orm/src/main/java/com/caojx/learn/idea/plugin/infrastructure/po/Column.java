package com.caojx.learn.idea.plugin.infrastructure.po;

public class Column {

    /**
     * 列注释
     */
    private String comment;

    /**
     * 列名
     */
    private String name;

    /**
     * 列类型
     */
    private int type;

    /**
     * 是否为主键
     */
    private boolean id;

    public Column(String comment, String name, int type) {
        this.comment = comment;
        this.name = name;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
