package com.caojx.learn.idea.plugin.markbook.action;

import com.caojx.learn.idea.plugin.markbook.data.DataCenter;
import com.caojx.learn.idea.plugin.markbook.dialog.AddNoteDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

/**
 * 添加一个右键点击之后的子菜单，获取编辑器中已经选中的文本
 */
public class PopupAction extends AnAction {

    /**
     * 获取编辑器中已经选中的文本
     * @param e
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        System.out.println("添加笔记的操作");

//        获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
//        获取选择的数据模型
        SelectionModel selectionModel = editor.getSelectionModel();
//        获取当前选择的文本
        String selectText = selectionModel.getSelectedText();
        System.out.println(selectText);

//
        // 选择文本
        DataCenter.SELECTED_TEXT = selectText;
        // 当前文件名
        DataCenter.CURRENT_FILE_NAME = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile().getName();
        DataCenter.CURRENT_FILE_TYPE =DataCenter.CURRENT_FILE_NAME.substring(DataCenter.CURRENT_FILE_NAME.lastIndexOf(".")+1);


//        弹出对话框
        AddNoteDialog addNoteDialog = new AddNoteDialog();
        addNoteDialog.show();;
    }
}
