package com.mdtech.speecher.api.form.qo;

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
public class FormQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Byte type;

    public FormQo() {
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
