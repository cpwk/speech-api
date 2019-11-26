package com.mdtech.speecher.common.util;

import java.io.InputStream;

public class R {

    public static InputStream getStream(String path) {
        return R.class.getResourceAsStream("/resources/" + path);
    }

}
