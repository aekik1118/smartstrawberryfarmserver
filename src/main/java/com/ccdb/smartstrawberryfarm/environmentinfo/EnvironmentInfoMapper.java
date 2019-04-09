package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EnvironmentInfoMapper {
    @Select("SELECT * FROM ENVIRONMENTINFO")
    EnvironmentInfo selectall();

    @Insert("INSERT INTO ENVIRONMENTINFO (id, area, farmname, humidity, temperature, brightness) VALUES (#{id}, #{area}, #{farmname}, #{humidity}, #{temperature}, #{brightness})")
    @SelectKey(statement = "SELECT nextval('seq_environment_info')", before = true ,keyProperty = "id", resultType = Long.class)
    Long createEnvironmentInfo(EnvironmentInfo environmentInfo);

}
