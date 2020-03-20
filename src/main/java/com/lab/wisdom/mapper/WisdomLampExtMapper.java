package com.lab.wisdom.mapper;

import com.lab.wisdom.model.wisdomLamp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface WisdomLampExtMapper {
    List<wisdomLamp> limit(Integer limit);
}
