package com.mdtech.speecher.common.exception;


import com.mdtech.speecher.common.entity.ErrorCode;

public class DetailedException extends ServiceException {

    public DetailedException(String msg) {
        super(ErrorCode.DETAILED.getCode(), msg);
    }

}
