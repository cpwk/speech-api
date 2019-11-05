package com.mdtech.zyedu.common.exception;


import com.mdtech.zyedu.common.entity.ErrorCode;

public class DetailedException extends ServiceException {

    public DetailedException(String msg) {
        super(ErrorCode.DETAILED.getCode(), msg);
    }

}
