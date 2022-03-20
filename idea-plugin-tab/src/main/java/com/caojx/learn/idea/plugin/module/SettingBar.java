package com.caojx.learn.idea.plugin.module;

import com.caojx.learn.idea.plugin.ui.GidConfig;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

/**
 * 设置-工具栏
 * DumbAwareAction 可以认为是一种不依附于IDEA 已有菜单的动作，需要依附于自定义的菜单或按钮
 */
public class SettingBar extends DumbAwareAction {

    private ViewBars viewBars;

    public SettingBar(ViewBars viewBars) {
        super("配置股票", "Click to setting", IconLoader.getIcon("/icons/config.svg"));
        this.viewBars = viewBars;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // 配置股票配置类实例
        ShowSettingsUtil.getInstance().editConfigurable(viewBars.getProject(), new GidConfig(viewBars.getConsoleUI()));
    }
}
