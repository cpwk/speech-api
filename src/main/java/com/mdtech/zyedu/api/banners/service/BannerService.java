package com.mdtech.zyedu.api.banners.service;

import com.mdtech.zyedu.api.banners.model.Banner;
import com.mdtech.zyedu.api.banners.qo.BannerQo;
import com.mdtech.zyedu.common.exception.ServiceException;

import java.util.List;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface BannerService {
    List<Banner> findAll(BannerQo qo, boolean admin) throws Exception;

    void removeBanner(Integer id);

    Banner findBanner(Integer id);

    void saveBanner(Banner banner) throws ServiceException;

    void updateStatus(Byte status, Integer id);
}
