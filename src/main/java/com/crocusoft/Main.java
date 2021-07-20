package com.crocusoft;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(PORT);
        tomcat.getConnector();

        Context context = tomcat.addWebapp("",
                new File("src/main/webapp").getAbsolutePath());

        File additionalWebInfClasses = new File("target/classes");
        WebResourceRoot resourceRoot = new StandardRoot(context);

        resourceRoot.addPreResources(new DirResourceSet(resourceRoot,
                "/WEB-INF/classes",
                additionalWebInfClasses.getAbsolutePath(),
                "/"));

        context.setResources(resourceRoot);

        tomcat.start();
        tomcat.getServer().await();
    }
}
