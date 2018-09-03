package com.mlimavieira.paintshop;

import java.io.File;
import java.net.URL;

public abstract class BaseTest {

    protected File getFile(String name) {
        URL resource = this.getClass().getResource("/files/" + name);

        return new File(resource.getFile());
    }

}
