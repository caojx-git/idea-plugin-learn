package com.caojx.learn.idea.plugin;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Action 测试通知
 */
public class NotificationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        NotificationGroup notificationGroup = new NotificationGroup("Custom Notification Group", NotificationDisplayType.BALLOON, true);
        notificationGroup.createNotification("测试通知", NotificationType.INFORMATION).notify(e.getProject());
    }
}
