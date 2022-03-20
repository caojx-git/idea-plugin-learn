package com.caojx.learn.idea.plugin.domain.service.impl;

import com.caojx.learn.idea.plugin.domain.model.vo.CodeGenContextVO;
import com.caojx.learn.idea.plugin.domain.service.AbstractProjectGenerator;
import com.caojx.learn.idea.plugin.infrastructure.po.*;
import com.caojx.learn.idea.plugin.infrastructure.utils.JavaType;
import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

public  class ProjectGeneratorImpl extends AbstractProjectGenerator {

    @Override
    protected void generateORM(Project project, CodeGenContextVO codeGenContext) {

        List<Table> tables = codeGenContext.getTables();
        for (Table table : tables) {
            List<Column> columns = table.getColumns();
            List<Field> fields = new ArrayList<>();

            for (Column column : columns) {
                Field field = new Field(column.getComment(), column.getName(),JavaType.convertType(column.getType()));
                field.setId(column.isId());
                fields.add(field);
            }

            // 生成PO
            Model model = new Model(table.getComment(), codeGenContext.getModelPackage() + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()), table.getName(), fields);
            writeFile(project, codeGenContext.getModelPackage(), model.getSimpleName() + ".java", "model.ftl", model);

            // 生成DAO
            Dao dao = new Dao(table.getComment(), codeGenContext.getDaoPackage() + "I" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()) + "Dao", model);
            writeFile(project, codeGenContext.getDaoPackage(), dao.getSimpleName() + ".java", "dao.ftl", dao);

            // 生成Mapper
            writeFile(project, codeGenContext.getMapperDir(), dao.getModel().getSimpleName() + "Mapper.xml", "mapperXml.ftl", dao);

        }
    }
}
