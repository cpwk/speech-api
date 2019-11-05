package com.mdtech.zyedu.api.trainer.repository;

import com.mdtech.zyedu.api.trainer.model.Trainer;
import com.mdtech.zyedu.common.reposiotry.BaseRepository;

public interface ITrainerRepository extends BaseRepository<Trainer, Integer> {

    Trainer findByNameAndStatus(String username, byte status);


}
