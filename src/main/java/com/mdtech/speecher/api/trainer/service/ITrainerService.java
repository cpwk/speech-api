package com.mdtech.speecher.api.trainer.service;

import com.mdtech.speecher.api.trainer.model.Trainer;
import com.mdtech.speecher.api.trainer.qo.TrainerQo;
import com.mdtech.speecher.common.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface ITrainerService {

    //trainer

    void save_trainer(Trainer trainer) throws ServiceException;

    Trainer getByName(String name);

    Page<Trainer> trainers(TrainerQo qo, boolean admin);

    void remove_trainer(int id, boolean root) throws ServiceException;

    Trainer trainer(int id, boolean init);

    void updateStatus(Byte status, Integer id);

}
