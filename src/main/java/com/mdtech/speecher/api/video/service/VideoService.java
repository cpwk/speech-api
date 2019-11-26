package com.mdtech.speecher.api.video.service;

import com.mdtech.speecher.api.video.model.Video;
import com.mdtech.speecher.api.video.qo.VideoQo;
import com.mdtech.speecher.common.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface VideoService {

    Page<Video> findAllVideo(VideoQo qo, boolean admin) throws Exception;

    void removeVideo(Integer id);

    Video findVideo(Integer id);

    void saveVideo(Video video) throws ServiceException;

    void updateStatus(Byte status, Integer id);
}
