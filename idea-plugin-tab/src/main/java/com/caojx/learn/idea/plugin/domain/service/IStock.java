package com.caojx.learn.idea.plugin.domain.service;

import com.caojx.learn.idea.plugin.domain.model.vo.Data;
import com.caojx.learn.idea.plugin.domain.model.vo.GoPicture;

import java.util.List;

public interface IStock {

    /**
     * 获取股票数据
     *
     * @param gids
     * @return
     */
    List<Data> queryPresetStockData(List<String> gids);

    /**
     * 获取股票k线图片
     *
     * @param gid
     * @return
     */
    GoPicture queryGidGoPicture(String gid);
}
