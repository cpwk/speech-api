package com.mdtech.zyedu.api.banners.service;

import com.mdtech.zyedu.api.banners.model.Banner;
import com.mdtech.zyedu.api.banners.qo.BannerQo;
import com.mdtech.zyedu.api.banners.repository.BannerRepository;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.exception.ServiceException;
import com.mdtech.zyedu.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:35
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
@Service
public class BannerServiceImpl implements BannerService {


    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> findAll(BannerQo qo, boolean admin) throws Exception {
        if (admin) {
            qo.setStatus(2);
        }
        return bannerRepository.findAll(qo);
    }

    @Override
    public void removeBanner(Integer id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public Banner findBanner(Integer id) {
        return bannerRepository.getOne(id);
    }

    @Override
    public void saveBanner(Banner banner) throws ServiceException {

        if (StringUtils.isEmpty(banner.getImg())) {
            throw new DetailedException("请上传图片");
        }
        bannerRepository.save(banner);
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Banner banner = bannerRepository.getOne(id);
        banner.setStatus(status);
        bannerRepository.save(banner);
    }
}
