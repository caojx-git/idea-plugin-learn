package com.caojx.learn.idea.plugin.infrastructure.data;

import com.caojx.learn.idea.plugin.domain.model.vo.ORMConfigVO;

public class DataState {

    private ORMConfigVO ormConfigVO = new ORMConfigVO();

    public ORMConfigVO getOrmConfigVO() {
        return ormConfigVO;
    }

    public void setOrmConfigVO(ORMConfigVO ormConfigVO) {
        this.ormConfigVO = ormConfigVO;
    }
}
