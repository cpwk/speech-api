package com.mdtech.speecher.api.admin.authority;

import com.mdtech.speecher.common.context.Context;
import com.mdtech.speecher.common.context.Contexts;
import com.mdtech.speecher.common.context.SessionWrap;

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
