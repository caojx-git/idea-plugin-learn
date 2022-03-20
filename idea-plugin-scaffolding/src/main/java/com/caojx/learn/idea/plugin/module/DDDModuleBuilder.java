package com.caojx.learn.idea.plugin.module;

import com.caojx.learn.idea.plugin.domain.service.IProjectGenerator;
import com.caojx.learn.idea.plugin.domain.service.impl.ProjectGeneratorImpl;
import com.caojx.learn.idea.plugin.infrastructure.ICONS;
import com.caojx.learn.idea.plugin.infrastructure.MsgBundle;
import com.caojx.learn.idea.plugin.infrastructure.PersistentDataService;
import com.caojx.learn.idea.plugin.ui.ProjectConfigUI;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.DisposeAwareRunnable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

/**
 * 配置步骤
 */
public class DDDModuleBuilder extends ModuleBuilder {

    private IProjectGenerator projectGenerator = new ProjectGeneratorImpl();


    @Override
    public Icon getNodeIcon() {
        return ICONS.SPRING_BOOT;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return StdModuleTypes.JAVA;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title)
    String getPresentableName() {
        return "Spring Boot";
    }

    @Override
    public String getDescription() {
        return MsgBundle.message("wizard.spring.boot.description");
    }

    /**
     * 重写 builderId 挂载自定义模板
     */
    @Nullable
    @Override
    public String getBuilderId() {
        return getClass().getName();
    }

    @Override
    public @Nullable ModuleWizardStep modifySettingsStep(@NotNull SettingsStep settingsStep) {
        ModuleNameLocationSettings moduleNameLocationSettings = settingsStep.getModuleNameLocationSettings();
        String artifactId = PersistentDataService.getInstance().getState().getProjectConfigVO().get_artifactId();
        if (null != moduleNameLocationSettings && !StringUtil.isEmptyOrSpaces(artifactId)) {
            moduleNameLocationSettings.setModuleName(artifactId);
        }
        return super.modifySettingsStep(settingsStep);
    }

    /**
     * 调用脚手架服务
     * @param rootModel
     * @throws ConfigurationException
     */
    @Override
    public void setupRootModel(@NotNull ModifiableRootModel rootModel) throws ConfigurationException {
        // 设置jdk
        if(this.myJdk != null){
            rootModel.setSdk(this.myJdk);
        }else{
            rootModel.inheritSdk();
        }

        // 生成工程路径
        String path = FileUtil.toSystemIndependentName(Objects.requireNonNull(getContentEntryPath()));
        new File(path).mkdirs();
        VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
        rootModel.addContentEntry(virtualFile);

        Project project = rootModel.getProject();

        // 创建工程结构,    另外这里需要用到 IDEA 提供的线程调用方法，new WriteCommandAction 才能正常创建。
        Runnable r = () -> new WriteCommandAction<VirtualFile>(project) {

            @Override
            protected void run(@NotNull Result<VirtualFile> result) throws Throwable {
                projectGenerator.doGenerator(project, getContentEntryPath(), PersistentDataService.getInstance().getState().getProjectConfigVO());
            }
        }.execute();

        if (ApplicationManager.getApplication().isUnitTestMode()
                || ApplicationManager.getApplication().isHeadlessEnvironment()) {
            r.run();
            return;
        }

        if (!project.isInitialized()) {
            StartupManager.getInstance(project).registerPostStartupActivity(DisposeAwareRunnable.create(r, project));
            return;
        }

        if (DumbService.isDumbAware(r)) {
            r.run();
        } else {
            DumbService.getInstance(project).runWhenSmart(DisposeAwareRunnable.create(r, project));
        }

    }

    /**
     * 在 createWizardSteps 方法中，把我们已经创建好的 DDDModuleConfigStep 添加工程配置步骤，可以自己定义需要的步骤，如果有多个可以依次添加。
     * 同时需要注意，只有重写了 getBuilderId() 方法后，你新增加的向导步骤才能生效。
     *
     * @param wizardContext
     * @param modulesProvider
     * @return
     */
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {

        // 添加工程配置步骤，可以自己定义需要的步骤，如果有多个可以依次添加
        DDDModuleConfigStep moduleConfigStep = new DDDModuleConfigStep(new ProjectConfigUI());

        return new ModuleWizardStep[]{moduleConfigStep};
    }
}
