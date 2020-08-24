package com.jxx.test.TestR;

import com.jxx.common.model.LogicalShow;
import org.junit.Test;

/**
 * @author Strange
 * @ClassName TestR.java
 * @Description TODO
 * @createTime 2020年06月28日 13:15:00
 */
public class TestR {

    @Test
    public void Test(){
        LogicalShow logicalShow = new LogicalShow();
        Integer orgId = 8;
        logicalShow = setlogicalShow(logicalShow,orgId);
        System.out.println(logicalShow.toString());
    }

    private LogicalShow setlogicalShow(LogicalShow logicalShow, Integer orgId) {
        logicalShow.setQuaFlag(true);
        logicalShow.setExpFlag(true);
        logicalShow.setVexpFlag(true);
        logicalShow.setStockFlag(true);
        logicalShow.setActionFlag(true);
        logicalShow.setCheckFlag(true);
        logicalShow.setLevelBFlag(true);
        logicalShow.setLevelCFlag(true);
        logicalShow.setLevelDFlag(true);
        logicalShow.setNdealFlag(true);
        logicalShow.setNquaFlag(true);
        logicalShow.setThreeFlag(true);
        return setaction(logicalShow,orgId);
    }

    private LogicalShow setaction(LogicalShow logicalShow, Integer orgId) {
        if(actionCheckOrgId(orgId)){
            logicalShow.setActionFlag(false);
        }
        return logicalShow;
    }

    private boolean actionCheckOrgId(Integer orgId) {
        return true;
    }

}
