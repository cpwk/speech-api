package com.mdtech.zyedu.api.trainer.qo;

import com.mdtech.zyedu.common.reposiotry.support.DataQueryObjectPage;
import com.mdtech.zyedu.common.reposiotry.support.QueryField;
import com.mdtech.zyedu.common.reposiotry.support.QueryType;

public class TrainerQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    private boolean sortAscending = true;

    public TrainerQo() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 2 ? null : status;
    }

    @Override
    public boolean isSortAscending() {
        return sortAscending;
    }

    @Override
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
