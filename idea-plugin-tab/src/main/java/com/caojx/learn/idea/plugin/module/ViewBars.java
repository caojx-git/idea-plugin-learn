package com.caojx.learn.idea.plugin.module;

import com.caojx.learn.idea.plugin.ui.ConsoleUI;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

/**
 * 窗体填充面板
 * 在填充面板中主要是在我们自定义的插件中，在左侧添加工具栏，其余位置添加股票展示面板。
 */
public class ViewBars extends SimpleToolWindowPanel {

    private Project project;

    private ConsoleUI consoleUI;

    public ViewBars(Project project) {
        super(false, true);

        this.project = project;
        consoleUI = new ConsoleUI();

        // 设置窗体侧边栏按钮
        DefaultActionGroup group = new DefaultActionGroup();
        // 设置tab
        group.add(new SettingBar(this));
        // 刷新tab
        group.add(new RefreshBar(this));

        // 创建工具栏
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false);
        toolbar.setTargetComponent(this);
        setToolbar(toolbar.getComponent());

//        // 分离器[可以参考Java布局管理器JSplitPane，可以对布局按照左右拆分，或者上线拆分]
//        // https://plugins.jetbrains.com/docs/intellij/misc-swing-components.html?from=jetbrains.org#jbsplitter
//        JBSplitter splitter = new JBSplitter(false);
//        splitter.setSplitterProportionKey("main.splitter.key");
//        splitter.setFirstComponent(consoleUI.getPanel());
//        splitter.setProportion(0.8f);
//        setContent(splitter);

        // 设置显示内容
        setContent(consoleUI.getPanel());
    }

    public Project getProject() {
        return project;
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }
}
