package com.mdtech.zyedu.api.common.service;

import com.mdtech.zyedu.common.entity.ValCode;
import com.mdtech.zyedu.common.exception.ServiceException;

public interface ICommonService {

    void saveValCode(Long key, ValCode valCode);

    ValCode getValCode(Long key) throws ServiceException;


}
