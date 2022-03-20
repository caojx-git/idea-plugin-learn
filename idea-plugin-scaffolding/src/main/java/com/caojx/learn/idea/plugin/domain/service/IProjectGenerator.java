package com.caojx.learn.idea.plugin.domain.service;

import com.caojx.learn.idea.plugin.domain.model.vo.ProjectConfigVO;
import com.intellij.openapi.project.Project;

public interface IProjectGenerator {

    /**
     * 生成项目
     *
     * @param project       项目
     * @param entryPath     项目绝对路径
     * @param projectConfig 项目配置信息
     */
    void doGenerator(Project project, String entryPath, ProjectConfigVO projectConfig);
}
