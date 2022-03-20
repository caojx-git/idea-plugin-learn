package com.caojx.learn.idea.plugin.domain.model.vo;

/**
 * 股票聚合数据模型
 */
public class Stock {

    /**
     * 股票数据
     */
    private Data data;

    /**
     * 股票k线图片
     */
    private GoPicture goPicture;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public GoPicture getGoPicture() {
        return goPicture;
    }

    public void setGoPicture(GoPicture goPicture) {
        this.goPicture = goPicture;
    }
}
