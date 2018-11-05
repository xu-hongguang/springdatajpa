package com.xhg.springdatajpa.config;

import com.xhg.springdatajpa.dao.RepliesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @author 16033
 */
@Configuration
public class RmiConfig {

    @Bean
    public RmiServiceExporter rmiServiceExporter(RepliesRepository repliesRepository){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(repliesRepository);
        rmiServiceExporter.setServiceName("repliesRepos");
        rmiServiceExporter.setServiceInterface(RepliesRepository.class);
//        rmiServiceExporter.setRegistryHost("rmi.replies.com");
        rmiServiceExporter.setRegistryPort(1199);
        return rmiServiceExporter;
    }

    @Bean
    public RmiProxyFactoryBean repliesResp(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1199/repliesRepos");
        rmiProxyFactoryBean.setServiceInterface(RepliesRepository.class);
        return rmiProxyFactoryBean;
    }
}
