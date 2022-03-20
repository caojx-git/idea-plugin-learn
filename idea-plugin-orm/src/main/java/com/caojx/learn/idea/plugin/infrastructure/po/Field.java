package com.caojx.learn.idea.plugin.infrastructure.po;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

public class Field {

    private String comment;
    private String columnName;
    private Class<?> type;
    private boolean id;

    public Field(String comment, String columnName, Class<?> type) {
        this.comment = comment;
        this.columnName = columnName;
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public String getTypeSimpleName() {
        return type.getSimpleName();
    }

    public String getTypeName() {
        return type.getName();
    }

    public String getName() {
        String str = columnName;
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    public boolean isImport() {
        String typeName = getTypeName();
        return !type.isPrimitive() && !"java.lang".equals(StringUtils.substringBeforeLast(typeName, "."));
    }
}
