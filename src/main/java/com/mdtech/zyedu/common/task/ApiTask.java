package com.mdtech.zyedu.common.task;


import com.mdtech.zyedu.common.context.SessionThreadLocal;

public abstract class ApiTask implements Runnable {

    @Override
    public final void run() {
        SessionThreadLocal.getInstance().set(null);
        try {
            doApiWork();
        } catch (Throwable t) {
            System.err.println(t);
        }
    }

    protected abstract void doApiWork() throws Exception;

}
