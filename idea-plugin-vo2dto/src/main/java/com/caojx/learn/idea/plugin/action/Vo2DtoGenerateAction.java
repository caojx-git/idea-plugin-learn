package com.caojx.learn.idea.plugin.action;

import com.caojx.learn.idea.plugin.application.IGenerateVo2Dto;
import com.caojx.learn.idea.plugin.domain.service.impl.GenerateVo2DtoImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class Vo2DtoGenerateAction extends AnAction {

    private IGenerateVo2Dto generateVo2Dto = new GenerateVo2DtoImpl();

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        try{
            generateVo2Dto.doGenerate(e.getProject(), e.getDataContext());
        }catch (Exception ex){
            Messages.showErrorDialog(e.getProject(), "错误提示", "请按规复制对象后，光标放到需要织入的对象上！");
        }
    }
}
