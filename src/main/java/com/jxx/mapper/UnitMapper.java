package com.jxx.mapper;

import com.jxx.common.model.Unit;

/**
 * <b>Description:</b><br> 产品单位管理mapper
 * @author leo.yang
 * @Note
 * <b>ProjectName:</b> dbcenter
 * <br><b>PackageName:</b> com.vedeng.dao.goods
 * <br><b>ClassName:</b> UnitMapper
 * <br><b>Date:</b> 2017年7月3日 下午3:48:19
 */
public interface UnitMapper {
	
    int deleteByPrimaryKey(Integer unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(Integer unitId);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);


    String getUnitName(String unit);
}