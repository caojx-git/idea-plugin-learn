package com.caojx.learn.idea.plugin.markbook.dialog;

import com.caojx.learn.idea.plugin.markbook.data.OneNoteData;
import com.caojx.learn.idea.plugin.markbook.data.DataCenter;
import com.caojx.learn.idea.plugin.markbook.data.DataConvert;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * 弹出对话框，获取用户编辑的笔记内容
 */
public class AddNoteDialog extends DialogWrapper {

    private EditorTextField etfTile;

    private EditorTextField etfMark;

    public AddNoteDialog() {
        super(true);
        init();
        setTitle("添加笔记注释");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        etfTile = new EditorTextField("笔记标题");
        etfMark = new EditorTextField("笔记内容");
        etfMark.setPreferredSize(new Dimension(200, 100));
        jPanel.add(etfTile, BorderLayout.NORTH);
        jPanel.add(etfMark, BorderLayout.CENTER);
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("添加笔记到列表");
        btnAdd.addActionListener(e -> {
            // 获取标题
            String title = etfTile.getText();
            // 笔记内容
            String mark = etfMark.getText();
            System.out.println(title + ":" + mark);

            // 创建一条笔记内容
            OneNoteData oneNoteData = new OneNoteData();
            oneNoteData.setTitle(title);
            oneNoteData.setMark(mark);
            oneNoteData.setSourceCode(DataCenter.SELECTED_TEXT);
            oneNoteData.setFileName(DataCenter.CURRENT_FILE_NAME);
            oneNoteData.setFileType(DataCenter.CURRENT_FILE_TYPE);

            // 笔记添加到列表中
            DataCenter.NOTE_LIST.add(oneNoteData);

            // 向表格中添加行记录
            DataCenter.TABLE_MODEL.addRow(DataConvert.convertTableRow(oneNoteData));

            System.out.println(DataCenter.NOTE_LIST);

            // 提示添加成功
            MessageDialogBuilder.yesNo("操作结果", "添加成功");
            // 不关闭窗口
            AddNoteDialog.this.dispose();
        });
        jPanel.add(btnAdd);
        return jPanel;
    }
}
