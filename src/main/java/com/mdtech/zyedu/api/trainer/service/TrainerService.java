package com.mdtech.zyedu.api.trainer.service;

import com.mdtech.zyedu.api.trainer.model.Trainer;
import com.mdtech.zyedu.api.trainer.qo.TrainerQo;
import com.mdtech.zyedu.api.trainer.repository.ITrainerRepository;
import com.mdtech.zyedu.common.cache.CacheOptions;
import com.mdtech.zyedu.common.cache.KvCacheFactory;
import com.mdtech.zyedu.common.cache.KvCacheWrap;
import com.mdtech.zyedu.common.entity.Constants;
import com.mdtech.zyedu.common.entity.ErrorCode;
import com.mdtech.zyedu.common.exception.DetailedException;
import com.mdtech.zyedu.common.exception.RepositoryException;
import com.mdtech.zyedu.common.exception.ServiceException;
import com.mdtech.zyedu.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

@Service
public class TrainerService implements ITrainerService {


    @Autowired
    private ITrainerRepository trainerRepository;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, Trainer> trainerCache;

    @PostConstruct
    public void init() {
        trainerCache = kvCacheFactory.create(new CacheOptions("trainer", 1, Constants.CACHE_REDIS_EXPIRE),
                new RepositoryProvider<Integer, Trainer>() {

                    @Override
                    public Trainer findByKey(Integer key) throws RepositoryException {
                        return trainerRepository.getOne(key);
                    }

                    @Override
                    public Map<Integer, Trainer> findByKeys(Collection<Integer> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(Trainer.class));
    }

    @Override
    public void save_trainer(Trainer trainer) throws ServiceException {
        Trainer oa = getByName(trainer.getName());

        if (trainer.getId() != null && trainer.getId() > 0) {
            if (oa == null)
                throw new ServiceException(ErrorCode.NOUSER.getCode());

            oa.setName(trainer.getName());
            oa.setIntro(trainer.getIntro());
            oa.setImg(trainer.getImg());

            trainerRepository.save(oa);
            trainerCache.remove(trainer.getId());
        } else {
            if (oa != null) {
                throw new DetailedException("用户名已存在");
            }
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
        trainerCache.remove(id);
    }

    @Override
    public Trainer trainer(int id, boolean init) {
        Trainer trainer = trainer(id);
        if (init) {
        }
        return trainer;
    }

    private Trainer trainer(int id) {
        return trainerCache.findByKey(id);
    }

    @Override
    public void updateStatus(Byte status, Integer id) {
        Trainer trainer = trainerRepository.getOne(id);
        trainer.setStatus(status);
        trainerRepository.save(trainer);
    }
}
