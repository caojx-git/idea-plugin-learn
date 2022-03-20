package com.caojx.learn.idea.plugin.domain.model.vo;

import com.caojx.learn.idea.plugin.infrastructure.po.Table;

import java.util.List;

public class CodeGenContextVO {

    /**
     * 数据模型包
     */
    private String modelPackage;

    /**
     * dao包
     */
    private String daoPackage;

    /**
     * mapper路径
     */
    private String mapperDir;

    /**
     * 表集合
     */
    private List<Table> tables;

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getMapperDir() {
        return mapperDir;
    }

    public void setMapperDir(String mapperDir) {
        this.mapperDir = mapperDir;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
