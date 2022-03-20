package com.caojx.learn.idea.plugin.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 配置持久化
 */
@State(name = "PersistentDataService", storages = @Storage("plugin.xml"))
public class PersistentDataService implements PersistentStateComponent<PersistentDataSate> {


    private PersistentDataSate persistentData =  new PersistentDataSate();

    public static PersistentDataService getInstance(){
        return ServiceManager.getService(PersistentDataService.class);
    }

    @Nullable
    @Override
    public PersistentDataSate getState() {
        return this.persistentData;
    }

    @Override
    public void loadState(@NotNull PersistentDataSate persistentDataSate) {
        this.persistentData = persistentDataSate;
    }
}
