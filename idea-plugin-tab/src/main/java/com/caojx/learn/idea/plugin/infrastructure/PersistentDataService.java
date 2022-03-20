package com.caojx.learn.idea.plugin.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 参考官网：https://plugins.jetbrains.com/docs/intellij/persistence.html
 * 数据持久化
 * persistentDataService 类需要使用到 IDEA 插件开发的提供的注解 @State 配置持久对象
 */
@State(name = "persistentDataService",storages = @Storage("plugin.xml"))
public class PersistentDataService implements PersistentStateComponent<DataState> {

    private DataState dataState = new DataState();

    /**
     * getInstance 方法来获取数据对象实例，那么在我们实际使用的时候就可以拿到我们配置的对象了并进行设置和读取数据
     * @return
     */
    public static PersistentDataService getInstance(){
        return ServiceManager.getService(PersistentDataService.class);
    }

    @Nullable
    @Override
    public DataState getState() {
        return dataState;
    }

    @Override
    public void loadState(@NotNull DataState dataState) {
        this.dataState = dataState;
    }


    public List<String> getGids(){
        return dataState.getGids();
    }
}
