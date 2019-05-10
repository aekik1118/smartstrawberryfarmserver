package com.ccdb.smartstrawberryfarm.pollution;


import com.ccdb.smartstrawberryfarm.environmentinfo.EnvironmentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface PollutionMapper {

    @Select("SELECT ispollution FROM pollution WHERE farmname = #{farmName} AND area = #{area}")
    Optional<Boolean> isPollutionFarmArea(String farmName, String area);

    @Insert("INSERT INTO pollution (farmname, area, isPollution) VALUES (#{farmname},  #{area}, #{isPollution})")
    int createPollution(Pollution pollution);
}
