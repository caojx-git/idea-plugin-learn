package com.caojx.learn.idea.plugin.infrastructure.data;

import com.caojx.learn.idea.plugin.domain.model.vo.ORMConfigVO;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "PersistentStateService", storages = @Storage("plugin.xml"))
public class PersistentStateService implements PersistentStateComponent<DataState> {

    private DataState state = new DataState();

    public static PersistentStateService getInstance(Project project) {
        return ServiceManager.getService(project, PersistentStateService.class);
    }


    @Nullable
    @Override
    public DataState getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull DataState dataState) {
        this.state = dataState;
    }

    public ORMConfigVO getORMConfig() {
        return state.getOrmConfigVO();
    }
}
