package com.caojx.learn.idea.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;

public class DialogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        Project project  = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        // 获取类路径
        String classPath = psiFile.getVirtualFile().getPath();

        // 获取项目名
        String name = psiFile.getVirtualFile().getName();

        Messages.showMessageDialog(project, name+"_"+classPath, name, Messages.getInformationIcon());
    }
}
