package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ChnRegister;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChnRegisterMapper {

    int insert(ChnRegister record);

    int updateByPrimaryKeySelective(ChnRegister record);

    ChnRegister getChnRegisterByNumber(ChnRegister chnRegister);

    ChnRegister getChnRegisterById(Integer i);
}