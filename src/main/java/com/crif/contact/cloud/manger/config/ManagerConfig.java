package com.crif.contact.cloud.manger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class ManagerConfig {
    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public ProperyHandler initPropertyHandler(){
        ProperyHandler properyHandler = new ProperyHandler();
        System.out.println("=============================Properies=============================");
        System.out.println("| username="+environment.getProperty("config.manager.git.username"));
        System.out.println("| password=****");
        System.out.println("| Repository URL="+environment.getProperty("config.manager.server.git.uri"));
        System.out.println("| Working directory="+environment.getProperty("config.manager.server.workDir"));
        System.out.println("=================================================================");

        properyHandler.setLogin(environment.getProperty("config.manager.git.username"));
        properyHandler.setPassword(environment.getProperty("config.manager.git.password"));
        properyHandler.setRepositoryUri(environment.getProperty("config.manager.server.git.uri"));
        properyHandler.setWorkDir(environment.getProperty("config.manager.server.workDir"));
        return properyHandler;
    }
}
