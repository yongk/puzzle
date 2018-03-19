package com.ruirui.multipart.embedded;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class EmbeddedTomcat {

    public static void main(String[] args) throws ServletException, LifecycleException {
        start(true);
    }

    public static void start(boolean await) throws ServletException, LifecycleException {
        String catalinaHome = System.getProperty("java.io.tmpdir");
        System.setProperty("catalina.home", catalinaHome);
        System.setProperty("catalina.base", catalinaHome);

        String docBase = "src/main/webapp";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9000);

        tomcat.addWebapp("", new File(docBase).getAbsolutePath());

        tomcat.start();
        if (await)
            tomcat.getServer().await();
    }
}
