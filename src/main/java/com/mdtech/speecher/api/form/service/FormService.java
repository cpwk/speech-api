package com.mdtech.speecher.api.form.service;

import com.mdtech.speecher.api.form.model.Form;
import com.mdtech.speecher.api.form.qo.FormQo;
import com.mdtech.speecher.api.heavywork.model.HeavyWork;
import com.mdtech.speecher.common.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * @author: MengKai
 * @create: 2019-08-2019-08-31-09:36
 * * * * * * * * * * * * * * * * * * * * * * * *
 **/
public interface FormService {
    Page<Form> findAllForm(FormQo qo);

    void saveForm(Form form) throws ServiceException;

    HeavyWork export_form(FormQo qo) throws Exception;

}
