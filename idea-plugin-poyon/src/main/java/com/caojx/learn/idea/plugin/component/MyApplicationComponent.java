package com.caojx.learn.idea.plugin.component;

import com.caojx.learn.idea.plugin.dialog.TuantDialog;
import com.intellij.openapi.components.ApplicationComponent;

public class MyApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent() {
        System.out.println("initComponent");

        TuantDialog tuantDialog = new TuantDialog();
        tuantDialog.show();
    }
}
