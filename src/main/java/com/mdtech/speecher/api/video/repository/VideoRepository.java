package com.mdtech.speecher.api.video.repository;


import com.mdtech.speecher.api.video.model.Video;
import com.mdtech.speecher.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface VideoRepository extends BaseRepository<Video, Integer> {
    Video findByTitle(String title);


    @Modifying
    @Query(value = "update Video set status=0 where type=:type and status=1")
    void updateAllStatusByType(Byte type);
}
