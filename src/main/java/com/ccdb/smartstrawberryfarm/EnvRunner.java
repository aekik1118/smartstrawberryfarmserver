package com.ccdb.smartstrawberryfarm;

import com.ccdb.smartstrawberryfarm.environmentinfo.EnvironmentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvRunner implements ApplicationRunner {

    @Autowired
    private EnvironmentInfoMapper environmentInfoMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(environmentInfoMapper.selectall());
    }
}