package com.mdtech.zyedu.api.campus.qo;

import com.mdtech.zyedu.common.reposiotry.support.DataQueryObjectPage;
import com.mdtech.zyedu.common.reposiotry.support.QueryField;
import com.mdtech.zyedu.common.reposiotry.support.QueryType;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-29-15:27
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public class CampusQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;


    public CampusQo() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 2 ? null : status;
    }

}
