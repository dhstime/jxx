package com.jxx.crawler.mapper;


import com.jxx.crawler.model.ChnRegister;

public interface ChnRegisterMapper {
    int deleteByPrimaryKey(Integer chnRegisterId);

    int insert(ChnRegister record);

    int insertSelective(ChnRegister record);

    ChnRegister selectByPrimaryKey(Integer chnRegisterId);

    int updateByPrimaryKeySelective(ChnRegister record);


    int updateByPrimaryKey(ChnRegister record);

    ChnRegister getChnRegisterByNumber(ChnRegister chnRegister);
}