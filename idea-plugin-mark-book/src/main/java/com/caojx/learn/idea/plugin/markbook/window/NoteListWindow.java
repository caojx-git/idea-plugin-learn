package com.caojx.learn.idea.plugin.markbook.window;

import com.caojx.learn.idea.plugin.markbook.processor.DefaultSourceNoteData;
import com.caojx.learn.idea.plugin.markbook.data.DataCenter;
import com.caojx.learn.idea.plugin.markbook.processor.MDFreeMarkProcessor;
import com.caojx.learn.idea.plugin.markbook.processor.Processor;
import com.intellij.notification.*;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI Form ToolWindow
 */
public class NoteListWindow {
    private JPanel contentPanel;
    private JTextField tfTopic;
    private JTable tbContent;
    private JButton btnCreate;
    private JButton btnClear;
    private JButton btnClose;

    /**
     * 初始化表格
     */
    private void initTable() {
        tbContent.setModel(DataCenter.TABLE_MODEL);
        tbContent.setEnabled(true);
    }

    public NoteListWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        initTable();

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                if (topic == null || "".equals(topic)) {
                    MessageDialogBuilder.yesNo("操作结果", "请输入文档标题").show();
                    return;
                }

                String fileName = topic + ".md";

                // 让用户选择文档生成的目录
//                https://plugins.jetbrains.com/docs/intellij/file-and-class-choosers.html
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                // 用户选择的了目录
                if (virtualFile != null) {
                    String path = virtualFile.getPath();
                    String fileFullPath = path + "/" + fileName;

                    DefaultSourceNoteData defaultSourceNoteData = new DefaultSourceNoteData();
                    defaultSourceNoteData.setFileName(fileFullPath);
                    defaultSourceNoteData.setTopic(topic);
                    defaultSourceNoteData.setNoteList(DataCenter.NOTE_LIST);
                    Processor processor = new MDFreeMarkProcessor();
                    try {
                        processor.process(defaultSourceNoteData);

                        // 发送通知
                        NotificationGroup notificationGroup = new NotificationGroup("Custom Notification Group", NotificationDisplayType.BALLOON, true);
                        notificationGroup.createNotification("生成文档成功", NotificationType.INFORMATION).notify(project);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataCenter.reset();
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                关闭窗口
                toolWindow.hide(null);
            }
        });
    }


    public JPanel getContentPanel() {
        return contentPanel;
    }
}
