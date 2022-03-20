package com.caojx.learn.idea.plugin.factory;

import com.caojx.learn.idea.plugin.infrastructure.ICONS;
import com.caojx.learn.idea.plugin.module.DDDModuleBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import com.intellij.platform.templates.BuilderBasedTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 项目创建模板
 */
public class TemplateFactory extends ProjectTemplatesFactory {

    @NotNull
    @Override
    public String[] getGroups() {
        return new String[]{"DDD脚手架"};
    }

    @Override
    public Icon getGroupIcon(String group) {
        return ICONS.DDD;
    }

    /**
     * 模板工厂的核心在于把我们用于创建 DDD 的步骤添加 createTemplates 方法中，这样算把整个创建自定义脚手架工程的链路就串联完成了。
     *
     * @param group
     * @param wizardContext
     * @return
     */
    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(@Nullable String group, WizardContext wizardContext) {
        return new ProjectTemplate[]{new BuilderBasedTemplate(new DDDModuleBuilder())};
    }
}
