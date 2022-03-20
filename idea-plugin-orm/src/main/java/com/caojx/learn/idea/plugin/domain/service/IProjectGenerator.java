package com.caojx.learn.idea.plugin.domain.service;

import com.caojx.learn.idea.plugin.domain.model.vo.CodeGenContextVO;
import com.intellij.openapi.project.Project;

public interface IProjectGenerator {

    /**
     * 生成代码
     *
     * @param project        项目
     * @param codeGenContext 上下文
     */
    void generation(Project project, CodeGenContextVO codeGenContext);
}
