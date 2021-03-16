package com.jxx.mapper;

import com.jxx.excel.Export;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Strange
 * @ClassName ExportMapper.java
 * @Description TODO
 * @createTime 2021年03月10日 22:09:00
 */
public interface ExportMapper {

    public List<Export> selectAll(@Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
