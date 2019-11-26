package com.mdtech.speecher.common.context;

import com.mdtech.speecher.api.admin.authority.AdminSessionWrap;
import com.mdtech.speecher.common.entity.ErrorCode;
import com.mdtech.speecher.common.exception.DetailedException;
import com.mdtech.speecher.common.exception.ServiceException;

public class Contexts {

    public static void set(Context context) {
        SessionThreadLocal.getInstance().set(context);
    }

    public static Context get() {
        return SessionThreadLocal.getInstance().get();
    }

    public static SessionWrap getSession() {
        return get().getSession();
    }

    public static Integer requestAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(ErrorCode.SESSIONTIMEOUT.getCode());
        }
        Integer adminId = sessionAdminId();
        if (adminId == null) {
            throw new DetailedException("need adminId");
        }
        return adminId;
    }

    public static Integer sessionAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer adminId = null;

        if (wrap instanceof AdminSessionWrap) {
            adminId = ((AdminSessionWrap) wrap).getAdmin().getId();
        }
        return adminId;
    }


}
