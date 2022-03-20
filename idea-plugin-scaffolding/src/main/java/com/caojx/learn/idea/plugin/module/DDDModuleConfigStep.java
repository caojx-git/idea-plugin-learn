package com.caojx.learn.idea.plugin.module;

import com.caojx.learn.idea.plugin.domain.model.vo.ProjectConfigVO;
import com.caojx.learn.idea.plugin.infrastructure.PersistentDataService;
import com.caojx.learn.idea.plugin.ui.ProjectConfigUI;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;

import javax.swing.*;

/**
 * 扩展步骤
 */
public class DDDModuleConfigStep extends ModuleWizardStep {

    private ProjectConfigUI projectConfigUI;

    public DDDModuleConfigStep(ProjectConfigUI projectConfigUI){
        this.projectConfigUI = projectConfigUI;
    }

    @Override
    public JComponent getComponent() {
        return projectConfigUI.getMainPanel();
    }

    @Override
    public void updateDataModel() {

    }

    @Override
    public boolean validate() throws ConfigurationException {
        // 获取配置信息，持久化
        ProjectConfigVO projectConfig = PersistentDataService.getInstance().getState().getProjectConfigVO();
        projectConfig.set_groupId(projectConfigUI.getGroupIdField().getText());
        projectConfig.set_artifactId(projectConfigUI.getArtifactIdField().getText());
        projectConfig.set_version(projectConfigUI.getVersionField().getText());
        projectConfig.set_package(projectConfigUI.getPackageField().getText());

        return super.validate();
    }
}
