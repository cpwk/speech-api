package com.mdtech.speecher.api.trainer.service;

import com.mdtech.speecher.api.trainer.model.Trainer;
import com.mdtech.speecher.api.trainer.qo.TrainerQo;
import com.mdtech.speecher.api.trainer.repository.ITrainerRepository;
import com.mdtech.speecher.common.entity.Constants;
import com.mdtech.speecher.common.exception.ServiceException;
import com.mdtech.speecher.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TrainerService implements ITrainerService {


    @Autowired
    private ITrainerRepository trainerRepository;

    @Override
    public void save_trainer(Trainer trainer) throws ServiceException {
        if (trainer.getId() != null && trainer.getId() > 0) {
            trainerRepository.save(trainer);
        } else {
            trainer.setCreatedAt(System.currentTimeMillis());
            trainerRepository.save(trainer);
        }
    }

    @Override
    public Trainer getByName(String username) {
        try {
            username = StringUtils.escapeSql(username);
            return trainerRepository.findByNameAndStatus(username, Constants.STATUS_OK);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Trainer> trainers(TrainerQo qo, boolean admin) {
        if (admin) {
            qo.setStatus(2);
        }
        return trainerRepository.findAll(qo);
    }

    @Override
    public void remove_trainer(int id, boolean root) throws ServiceException {
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer trainer(int id, boolean init) {
        Trainer trainer = trainer(id);
        if (init) {
        }
        return trainer;
    }

    private Trainer trainer(int id) {
        return trainerRepository.findById(id).get();
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Trainer trainer = trainerRepository.getOne(id);
        trainer.setStatus(status);
        trainerRepository.save(trainer);
    }
}
