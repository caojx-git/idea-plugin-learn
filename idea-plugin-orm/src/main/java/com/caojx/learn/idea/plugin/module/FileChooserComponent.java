package com.caojx.learn.idea.plugin.module;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 文件选择组件
 */
public class FileChooserComponent {

    private final Project project;

    private final FileEditorManager fileEditorManager;

    public FileChooserComponent(Project project) {
        this.project = project;
        this.fileEditorManager = FileEditorManager.getInstance(project);
    }

    public static FileChooserComponent getInstance(@NotNull Project project) {
        return new FileChooserComponent(project);
    }

    public VirtualFile showFolderSelectionDialog(@NotNull String title, @Nullable VirtualFile toSelect, @Nullable VirtualFile... roots) {
        final FileChooserDescriptor fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        fileChooserDescriptor.setTitle(title);
        if (roots != null) {
            fileChooserDescriptor.setRoots(roots);
        }
        return FileChooser.chooseFile(fileChooserDescriptor, project, toSelect);
    }


}
