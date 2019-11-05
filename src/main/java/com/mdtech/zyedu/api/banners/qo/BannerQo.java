package com.mdtech.zyedu.api.banners.qo;

import com.mdtech.zyedu.common.reposiotry.support.DataQueryObjectSort;
import com.mdtech.zyedu.common.reposiotry.support.QueryField;
import com.mdtech.zyedu.common.reposiotry.support.QueryType;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-29-15:27
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public class BannerQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    public BannerQo() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 2 ? null : status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
