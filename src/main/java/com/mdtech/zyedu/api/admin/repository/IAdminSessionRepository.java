package com.mdtech.zyedu.api.admin.repository;

import com.mdtech.zyedu.api.admin.model.AdminSession;
import com.mdtech.zyedu.common.reposiotry.BaseRepository;

public interface IAdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}