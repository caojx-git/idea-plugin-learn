package com.caojx.learn.idea.plugin.ui;

import com.caojx.learn.idea.plugin.infrastructure.PersistentDataService;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * GidConfig 股票配置类
 * 实现Configurable，当确认使用配置时，会调用apply方法
 */
public class GidConfig implements Configurable {
    private JPanel mainPanel;
    private JTextField gidTextField;
    private JPanel settingPanel;

    private ConsoleUI consoleUI;

    public GidConfig(ConsoleUI consoleUI){
        this.consoleUI = consoleUI;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        // 清空之前持久化保存的股票编码
        List<String> gidList = PersistentDataService.getInstance().getGids();
        gidList.clear();

        // 重新设置持久化股票编码
        String[] gids = gidTextField.getText().trim().split(",");
        for (String gid: gids){
            gidList.add(gid.trim());
        }

        // 刷新数据
        consoleUI.addRows(gidList);

    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Stock";
    }
}
