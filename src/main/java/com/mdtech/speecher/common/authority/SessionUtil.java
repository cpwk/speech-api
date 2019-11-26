package com.mdtech.speecher.common.authority;

import com.mdtech.speecher.api.admin.authority.AdminSessionWrap;
import com.mdtech.speecher.api.admin.model.Admin;
import com.mdtech.speecher.api.admin.model.AdminSession;
import com.mdtech.speecher.api.admin.service.IAdminService;
import com.mdtech.speecher.api.trainer.service.ITrainerService;
import com.mdtech.speecher.common.context.SessionWrap;
import com.mdtech.speecher.common.entity.Constants;
import com.mdtech.speecher.common.entity.ErrorCode;
import com.mdtech.speecher.common.exception.ServiceException;
import com.mdtech.speecher.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class SessionUtil {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ITrainerService trainerService;


    public static Map<String, SessionWrap> map = new HashMap<>();

    public static SessionWrap getSession(String token) {
        return map.get(token);
    }

    public static boolean tokenTimeout(String token) {
        if (map.get(token) == null) {
            return true;
        } else {
            SessionWrap wrap = map.get(token);
            if (wrap == null) {
                return true;
            }
            long currT = System.currentTimeMillis();
            if (wrap instanceof AdminSessionWrap) {
                AdminSession session = ((AdminSessionWrap) wrap).getAdminSession();
                return session == null || session.getExpireAt() <= currT;
            } else {
                return true;
            }
        }

    }

    public static void putSession(String token, SessionWrap sess) {
        if (map == null || map.isEmpty()) {
            map = new HashMap<>();
        }
        map.put(token, sess);
    }

    public static void removeSession(String token) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (token.equals(key)) {
                iterator.remove();
                map.remove(key);
            }
        }
    }

    public SessionWrap adminPermissionCheck(Enum type, String token, String permission) throws ServiceException {

        if (tokenTimeout(token)) {
            long currT = System.currentTimeMillis();
            if (type == AdminType.ADMIN) {
                AdminSession session = adminService.adminSession(token);

                if (session != null && session.getExpireAt() > currT) {
                    Admin admin = adminService.admin(session.getAdminId(), true);
                    if (admin != null && admin.getStatus() == Constants.STATUS_OK) {
                        AdminSessionWrap wrap = new AdminSessionWrap(admin, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ErrorCode.NO_PERMISSION.getCode());
                    }
                } else {
                    throw new ServiceException(ErrorCode.SESSIONTIMEOUT.getCode());
                }
            }
        }

        {
            boolean pass = false;

            SessionWrap wrap = getSession(token);
            if (wrap == null) {
                throw new ServiceException(ErrorCode.SESSIONTIMEOUT.getCode());
            }

            if (StringUtils.isEmpty(permission) || permission.equals("NONE")) {
                pass = true;
            } else {
                List<String> ps = new ArrayList<>();
                if (wrap instanceof AdminSessionWrap) {
                    Admin admin = ((AdminSessionWrap) wrap).getAdmin();
                    ps = admin.getRole().getPermissions();
                }
                pass = ps.contains(permission);
            }

            if (pass) {
                return wrap;
            } else {
                throw new ServiceException(ErrorCode.NO_PERMISSION.getCode());
            }
        }

    }

}
