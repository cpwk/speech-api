package com.mdtech.zyedu.api.admin.qo;

import com.mdtech.zyedu.common.reposiotry.support.DataQueryObjectPage;
import com.mdtech.zyedu.common.reposiotry.support.QueryField;
import com.mdtech.zyedu.common.reposiotry.support.QueryType;

public class AdminSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "adminId")
    private Integer adminId;

    public AdminSessionQo() {
    }

    public AdminSessionQo(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

}
