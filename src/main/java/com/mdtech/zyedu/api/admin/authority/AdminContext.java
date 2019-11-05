package com.mdtech.zyedu.api.admin.authority;

import com.mdtech.zyedu.common.context.Context;
import com.mdtech.zyedu.common.context.Contexts;
import com.mdtech.zyedu.common.context.SessionWrap;

public class AdminContext {

    public static AdminSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof AdminSessionWrap)) {
            return null;
        }
        return (AdminSessionWrap) session;
    }

}
