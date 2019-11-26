package com.mdtech.speecher.api.video.qo;

import com.mdtech.speecher.common.reposiotry.support.DataQueryObjectPage;
import com.mdtech.speecher.common.reposiotry.support.QueryField;
import com.mdtech.speecher.common.reposiotry.support.QueryType;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-29-15:27
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public class VideoQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    public VideoQo() {

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
