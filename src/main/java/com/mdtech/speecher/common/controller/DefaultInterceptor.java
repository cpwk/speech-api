package com.mdtech.speecher.common.controller;

import com.mdtech.speecher.api.admin.authority.AdminPermission;
import com.mdtech.speecher.common.authority.AdminType;
import com.mdtech.speecher.common.authority.RequiredPermission;
import com.mdtech.speecher.common.authority.SessionUtil;
import com.mdtech.speecher.common.context.Context;
import com.mdtech.speecher.common.context.Contexts;
import com.mdtech.speecher.common.context.SessionThreadLocal;
import com.mdtech.speecher.common.context.SessionWrap;
import com.mdtech.speecher.common.entity.ApiParams;
import com.mdtech.speecher.common.entity.ErrorCode;
import com.mdtech.speecher.common.exception.DetailedException;
import com.mdtech.speecher.common.util.WebUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultInterceptor extends SessionUtil implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (CrossDomainHandler.handle(request, response)) {
            return false;
        }

        Context wrapper = new Context();
        wrapper.setRequestIp(WebUtils.getRemoteAddress(request));
        SessionThreadLocal.getInstance().set(wrapper);

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean authorized = false;
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        if (requiredPermission == null) {
            throw new DetailedException("请检查方法和参数");
        }
        for (AdminType adminType : requiredPermission.adminType()) {
            if (adminType == AdminType.ADMIN) {
                //多个权限满足其一即可
                for (AdminPermission permission : requiredPermission.adminPermission()) {
                    authorized = findSessionWrap(adminType, request, permission.name()) != null;
                    if (authorized) {
                        break;
                    }
                }
            } else if (adminType == AdminType.TRAINER || adminType == AdminType.TRAINEE) {
                authorized = findSessionWrap(adminType, request, null) != null;
                if (authorized) {
                    break;
                }
            } else {
                // no session
                authorized = true;
            }
            if (authorized) {
                break;
            }
        }
        if (!authorized) {
            throw new DetailedException(ErrorCode.SESSIONTIMEOUT.getMsg());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private SessionWrap findSessionWrap(Enum type, HttpServletRequest request, String permission) throws Exception {
        String token = WebUtils.getHeader(request, ApiParams.ADMIN_TOKEN);
        SessionWrap wrap = adminPermissionCheck(type, token, permission);
        Contexts.get().setSession(wrap);
        return wrap;
    }

}