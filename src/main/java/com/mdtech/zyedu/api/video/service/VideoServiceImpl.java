package com.mdtech.zyedu.api.video.service;

import com.mdtech.zyedu.api.video.model.Video;
import com.mdtech.zyedu.api.video.qo.VideoQo;
import com.mdtech.zyedu.api.video.repository.VideoRepository;
import com.mdtech.zyedu.common.entity.ErrorCode;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.exception.ServiceException;
import com.mdtech.zyedu.common.util.StringUtils;
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
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Page<Video> findAllVideo(VideoQo qo, boolean admin) throws Exception {
        if (admin) {
            qo.setStatus(2);
        }
        return videoRepository.findAll(qo);
    }

    @Override
    public void removeVideo(Integer id) {
        videoRepository.deleteById(id);
    }

    @Override
    public Video findVideo(Integer id) {
        return videoRepository.getOne(id);
    }

    @Override
    public void saveVideo(Video video) throws ServiceException {

        if (StringUtils.isEmpty(video.getTitle())) {
            throw new DetailedException("请填写视频标题");
        }
        if (StringUtils.isEmpty(video.getImg())) {
            throw new DetailedException("请上传视频封面");
        }
        if (StringUtils.isEmpty(video.getUrl())) {
            throw new DetailedException("请上传视频");
        }
        if (video.getType() == null) {
            throw new DetailedException("请选择类型");
        }
        if (video.getType().equals(3)) {
            if (StringUtils.isEmpty(video.getVideoSummary())) {
                throw new DetailedException("请填写视频摘要");
            }

            if (StringUtils.isEmpty(video.getVideoIntro())) {
                throw new DetailedException("请填写视频简介");
            }
        }
        Video ov = videoRepository.findByTitle(video.getTitle());
        if (video.getId() != null && video.getId() > 0) {
            if (ov == null)
                throw new ServiceException(ErrorCode.NOUSER.getCode());
            videoRepository.save(video);
        } else {
            if (ov != null) {
                throw new DetailedException("标题已存在");
            }
            video.setCreatedAt(System.currentTimeMillis());
            videoRepository.save(video);
        }
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Video video = videoRepository.getOne(id);
        video.setStatus(status);
        videoRepository.save(video);
    }
}
