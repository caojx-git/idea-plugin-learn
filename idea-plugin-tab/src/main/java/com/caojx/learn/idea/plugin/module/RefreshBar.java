package com.caojx.learn.idea.plugin.module;

import com.caojx.learn.idea.plugin.infrastructure.PersistentDataService;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

/**
 * 刷新-工具栏
 * DumbAwareAction 可以认为是一种不依附于IDEA 已有菜单的动作，需要依附于自定义的菜单或按钮
 */
public class RefreshBar extends DumbAwareAction {

    private ViewBars viewBars;


    public RefreshBar(ViewBars viewBars) {
        super("刷新指数", "Click to refresh", IconLoader.getIcon("/icons/refresh.svg"));
        this.viewBars = viewBars;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // 触发股票刷新
        viewBars.getConsoleUI().addRows(PersistentDataService.getInstance().getGids());
    }
}
