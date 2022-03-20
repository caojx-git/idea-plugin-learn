package com.caojx.learn.idea.plugin.infrastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据持久化对象，保存股票代码配置
 */
public class DataState {

    /**
     * 自选股票代码集合
     */
    private List<String> gids = new ArrayList<>();

    public List<String> getGids() {
        return gids;
    }

    public void setGids(List<String> gids) {
        this.gids = gids;
    }
}
