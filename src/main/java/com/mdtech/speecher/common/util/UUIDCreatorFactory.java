package com.mdtech.speecher.common.util;

import com.sunnysuperman.commons.model.ObjectId;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;

public final class UUIDCreatorFactory {

    public static class UUIDCreator {
        private final AtomicInteger counter = new AtomicInteger(new SecureRandom().nextInt());

        public String create() {
            return new ObjectId(counter).toHexString();
        }
    }

    private static final UUIDCreator DEFAULT_CREATOR = UUIDCreatorFactory.get();

    public static UUIDCreator get() {
        return new UUIDCreator();
    }

    public static UUIDCreator getDefault() {
        return DEFAULT_CREATOR;
    }

}
