package com.mdtech.speecher.api.admin.repository;

import com.mdtech.speecher.api.admin.model.AdminSession;
import com.mdtech.speecher.common.reposiotry.BaseRepository;

public interface IAdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}