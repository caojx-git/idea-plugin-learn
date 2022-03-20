package com.caojx.learn.idea.plugin.dialog;

import com.caojx.learn.idea.plugin.util.ContentUtil;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * 对话框类，继承IDEA的DialogWrapper
 */
public class TuantDialog extends DialogWrapper {


    private JLabel jLabel;

    public TuantDialog() {
        super(true);
        setTitle("每天一碗毒鸡汤");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        // 创建一个面板，设置布局为边界布局
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 文字标签
        jLabel = new JLabel("毒鸡汤内容");
        // 字体大小
        jLabel.setPreferredSize(new Dimension(100, 100));
        // 将文字标签放在面板中间
        centerPanel.add(jLabel, BorderLayout.CENTER);
        return centerPanel;
    }


    @Override
    protected JComponent createSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout());
        JButton jButton = new JButton("再干一碗");
        jButton.addActionListener(e->{
            String content = ContentUtil.getContent();
            jLabel.setText(content);
        });
        southPanel.add(jButton);
        return southPanel;
    }
}
