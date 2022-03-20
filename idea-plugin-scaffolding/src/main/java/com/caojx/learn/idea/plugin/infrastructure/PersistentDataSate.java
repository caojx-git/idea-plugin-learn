package com.caojx.learn.idea.plugin.infrastructure;

import com.caojx.learn.idea.plugin.domain.model.vo.ProjectConfigVO;

/**
 * 持久化配置数据
 */
public class PersistentDataSate {

    public ProjectConfigVO getProjectConfigVO() {
        return projectConfigVO;
    }

    public void setProjectConfigVO(ProjectConfigVO projectConfigVO) {
        this.projectConfigVO = projectConfigVO;
    }

    private ProjectConfigVO projectConfigVO = new ProjectConfigVO();
}
