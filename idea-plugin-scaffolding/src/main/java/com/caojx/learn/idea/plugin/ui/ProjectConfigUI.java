package com.caojx.learn.idea.plugin.ui;

import javax.swing.*;

/**
 * 配置工厂信息的 UI 窗体
 */
public class ProjectConfigUI {
    private JPanel mainPanel;
    private JTextField groupIdField;
    private JTextField artifactIdField;
    private JTextField versionField;
    private JTextField packageField;


    public JPanel getMainPanel(){
        return this.mainPanel;
    }

    public JTextField getGroupIdField() {
        return groupIdField;
    }

    public JTextField getArtifactIdField() {
        return artifactIdField;
    }

    public JTextField getVersionField() {
        return versionField;
    }

    public JTextField getPackageField() {
        return packageField;
    }
}
