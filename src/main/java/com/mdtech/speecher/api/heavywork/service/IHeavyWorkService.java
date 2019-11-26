package com.mdtech.speecher.api.heavywork.service;


import com.mdtech.speecher.api.heavywork.model.HeavyWork;

public interface IHeavyWorkService {

    HeavyWork create(String owner) throws Exception;

    HeavyWork getById(Integer id, String secret) throws Exception;

    void updateProgress(Integer id, int progress);

    void updateFailed(Integer id, String errors);

    void updateSuccess(Integer id, String output);

}
