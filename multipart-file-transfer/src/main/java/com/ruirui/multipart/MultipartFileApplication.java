package com.ruirui.multipart;

import com.ruirui.multipart.embedded.EmbeddedJetty;
import com.ruirui.multipart.embedded.EmbeddedTomcat;

public class MultipartFileApplication {

    public static void main(String[] args) throws Exception {
        EmbeddedTomcat.start(false);
        EmbeddedJetty.main(args);
    }
}
