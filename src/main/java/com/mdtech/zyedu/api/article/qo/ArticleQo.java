package com.mdtech.zyedu.api.article.qo;

import com.mdtech.zyedu.common.reposiotry.support.DataQueryObjectPage;
import com.mdtech.zyedu.common.reposiotry.support.QueryField;
import com.mdtech.zyedu.common.reposiotry.support.QueryType;

public class ArticleQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    public ArticleQo() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
