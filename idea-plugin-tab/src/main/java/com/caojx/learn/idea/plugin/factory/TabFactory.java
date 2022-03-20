package com.caojx.learn.idea.plugin.factory;

import com.caojx.learn.idea.plugin.module.ViewBars;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class TabFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 窗体
        ViewBars viewBars = new ViewBars(project);

        // 获取工厂实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        // 获取toolWindow 显示的内容
        Content content = contentFactory.createContent(viewBars, "股票", false);

        // 设置toolWindow 显示内容
        toolWindow.getContentManager().addContent(content);
    }
}
