package com.caojx.learn.idea.plugin.action;

import com.caojx.learn.idea.plugin.domain.service.IProjectGenerator;
import com.caojx.learn.idea.plugin.domain.service.impl.ProjectGeneratorImpl;
import com.caojx.learn.idea.plugin.ui.ORMSettingsUI;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;

/**
 * 右键菜单，打开ORMSettingsUI
 */
public class CodeGenerateAction extends AnAction {

    IProjectGenerator projectGenerator = new ProjectGeneratorImpl();

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);

        // 显示配置窗体
        ShowSettingsUtil.getInstance().editConfigurable(project, new ORMSettingsUI(project, projectGenerator));
    }
}
