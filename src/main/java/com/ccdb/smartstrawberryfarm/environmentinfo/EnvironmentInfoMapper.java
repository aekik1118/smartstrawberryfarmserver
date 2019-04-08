package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EnvironmentInfoMapper {
    @Select("SELECT * FROM ENVIRONMENTINFO")
    EnvironmentInfo selectall();
}
