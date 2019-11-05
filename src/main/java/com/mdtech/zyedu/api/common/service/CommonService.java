package com.mdtech.zyedu.api.common.service;

import com.mdtech.zyedu.api.trainer.repository.ITrainerRepository;
import com.mdtech.zyedu.common.cache.CacheOptions;
import com.mdtech.zyedu.common.cache.KvCacheFactory;
import com.mdtech.zyedu.common.cache.KvCacheWrap;
import com.mdtech.zyedu.common.entity.ErrorCode;
import com.mdtech.zyedu.common.entity.ValCode;
import com.mdtech.zyedu.common.exception.RepositoryException;
import com.mdtech.zyedu.common.exception.ServiceException;
import com.mdtech.zyedu.common.service.TaskService;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

@Service
public class CommonService implements ICommonService {

    @Autowired
    private TaskService taskService;


    @Autowired
    private ITrainerRepository trainerRepository;


    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Long, ValCode> valCodeCache;

    @PostConstruct
    public void init() {
        valCodeCache = kvCacheFactory.create(new CacheOptions("val_code", 2, 600),
                new RepositoryProvider<Long, ValCode>() {

                    @Override
                    public ValCode findByKey(Long key) {
                        throw new RuntimeException();
                    }

                    @Override
                    public Map<Long, ValCode> findByKeys(Collection<Long> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(ValCode.class));
    }

    @Override
    public void saveValCode(Long key, ValCode valCode) {
        valCodeCache.save(key, valCode);
    }

    @Override
    public ValCode getValCode(Long key) throws ServiceException {
        try {
            return valCodeCache.findByKey(key);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ERROR_VALCODE.getCode());
        }
    }


}

