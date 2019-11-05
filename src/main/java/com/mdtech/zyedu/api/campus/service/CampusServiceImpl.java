package com.mdtech.zyedu.api.campus.service;

import com.mdtech.zyedu.api.campus.model.Campus;
import com.mdtech.zyedu.api.campus.qo.CampusQo;
import com.mdtech.zyedu.api.campus.repository.CampusRepository;
import com.mdtech.zyedu.common.entity.ErrorCode;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:35
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
@Service
public class CampusServiceImpl implements CampusService {


    @Autowired
    private CampusRepository campusRepository;

    @Override
    public Page<Campus> findAllCampus(CampusQo qo, boolean admin) throws Exception {
        if (admin) {
            qo.setStatus(2);
        }
        return campusRepository.findAll(qo);
    }

    @Override
    public void removeCampus(Integer id) {
        campusRepository.deleteById(id);
    }

    @Override
    public Campus findCampus(Integer id) {
        return campusRepository.getOne(id);
    }

    @Override
    public void saveCampus(Campus campus) throws ServiceException {
        Campus om = campusRepository.findByName(campus.getName());

        if (campus.getId() != null && campus.getId() > 0) {
            if (om == null) {
                throw new ServiceException(ErrorCode.NOUSER.getCode());
            }
            om.setStatus(campus.getStatus());
            om.setCompany(campus.getCompany());
            om.setName(campus.getName());
            campusRepository.save(om);
        } else {
            if (om != null) {
                throw new DetailedException("用户名已存在");
            }
            campus.setCreatedAt(System.currentTimeMillis());
            campusRepository.save(campus);
        }
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Campus campus = campusRepository.getOne(id);
        campus.setStatus(status);
        campusRepository.save(campus);
    }
}
