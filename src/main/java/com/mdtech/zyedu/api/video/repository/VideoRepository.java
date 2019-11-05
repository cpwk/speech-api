package com.mdtech.zyedu.api.video.repository;


import com.mdtech.zyedu.api.video.model.Video;
import com.mdtech.zyedu.common.reposiotry.BaseRepository;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface VideoRepository extends BaseRepository<Video, Integer> {
    Video findByTitle(String title);
}
