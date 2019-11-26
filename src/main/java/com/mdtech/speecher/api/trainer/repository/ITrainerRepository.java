package com.mdtech.speecher.api.trainer.repository;

import com.mdtech.speecher.api.trainer.model.Trainer;
import com.mdtech.speecher.common.reposiotry.BaseRepository;

public interface ITrainerRepository extends BaseRepository<Trainer, Integer> {

    Trainer findByNameAndStatus(String username, byte status);


}
