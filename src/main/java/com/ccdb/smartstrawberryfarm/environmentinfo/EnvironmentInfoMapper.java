package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnvironmentInfoMapper {
    @Select("SELECT * FROM ENVIRONMENTINFO WHERE farmname = #{farmName} AND area = #{farmArea} AND date_prod >= to_timestamp(#{beginTime},'YYYY-MM-DD') AND date_prod < to_timestamp(#{endTime},'YYYY-MM-DD')")
    List<EnvironmentInfo> selectallbyTime(String farmName, String farmArea, String beginTime, String endTime);

    @Insert("INSERT INTO ENVIRONMENTINFO (id, area, farmname, humidity, temperature, brightness) VALUES (#{id}, #{area}, #{farmname}, #{humidity}, #{temperature}, #{brightness})")
    @SelectKey(statement = "SELECT nextval('seq_environment_info')", before = true ,keyProperty = "id", resultType = Long.class)
    Long createEnvironmentInfo(EnvironmentInfo environmentInfo);
}
