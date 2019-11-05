package com.mdtech.zyedu.api.campus.service;

import com.mdtech.zyedu.api.campus.model.Campus;
import com.mdtech.zyedu.api.campus.qo.CampusQo;
import com.mdtech.zyedu.common.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface CampusService {
    Page<Campus> findAllCampus(CampusQo qo, boolean admin) throws Exception;

    void removeCampus(Integer id);

    Campus findCampus(Integer id);

    void saveCampus(Campus campus) throws ServiceException;

    void updateStatus(Byte status, Integer id);
}
