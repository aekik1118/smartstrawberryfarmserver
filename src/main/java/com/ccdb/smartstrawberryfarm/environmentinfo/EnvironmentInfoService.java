package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentInfoService {

    @Autowired
    EnvironmentInfoMapper environmentInfoMapper;


}
