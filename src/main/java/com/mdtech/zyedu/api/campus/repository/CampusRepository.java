package com.mdtech.zyedu.api.campus.repository;


import com.mdtech.zyedu.api.campus.model.Campus;
import com.mdtech.zyedu.common.reposiotry.BaseRepository;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface CampusRepository extends BaseRepository<Campus, Integer> {
    Campus findByName(String name);
}
