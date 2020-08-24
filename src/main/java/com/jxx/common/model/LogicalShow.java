package com.jxx.common.model;

/**
 * @author Strange
 * @ClassName Logical.java
 * @Description TODO
 * @createTime 2020年06月28日 13:20:00
 */
public class LogicalShow {
    //在库总数量
    private Boolean stockFlag;
    //合格库
    private Boolean quaFlag;
    //活动库
    private Boolean actionFlag;
    //超近效期
    private Boolean vexpFlag;
    //等级B
    private Boolean levelBFlag;
    //等级C
    private Boolean levelCFlag;
    //等级D
    private Boolean levelDFlag;
    //三方库
    private Boolean threeFlag;
    //近效期库
    private Boolean expFlag;
    //待检测库
    private Boolean checkFlag;
    //不合格库
    private Boolean nquaFlag;
    //待处理库
    private Boolean ndealFlag;

    public Boolean getStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(Boolean stockFlag) {
        this.stockFlag = stockFlag;
    }

    public Boolean getQuaFlag() {
        return quaFlag;
    }

    public void setQuaFlag(Boolean quaFlag) {
        this.quaFlag = quaFlag;
    }

    public Boolean getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(Boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    public Boolean getVexpFlag() {
        return vexpFlag;
    }

    public void setVexpFlag(Boolean vexpFlag) {
        this.vexpFlag = vexpFlag;
    }

    public Boolean getLevelBFlag() {
        return levelBFlag;
    }

    public void setLevelBFlag(Boolean levelBFlag) {
        this.levelBFlag = levelBFlag;
    }

    public Boolean getLevelCFlag() {
        return levelCFlag;
    }

    public void setLevelCFlag(Boolean levelCFlag) {
        this.levelCFlag = levelCFlag;
    }

    public Boolean getLevelDFlag() {
        return levelDFlag;
    }

    public void setLevelDFlag(Boolean levelDFlag) {
        this.levelDFlag = levelDFlag;
    }

    public Boolean getThreeFlag() {
        return threeFlag;
    }

    public void setThreeFlag(Boolean threeFlag) {
        this.threeFlag = threeFlag;
    }

    public Boolean getExpFlag() {
        return expFlag;
    }

    public void setExpFlag(Boolean expFlag) {
        this.expFlag = expFlag;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Boolean getNquaFlag() {
        return nquaFlag;
    }

    public void setNquaFlag(Boolean nquaFlag) {
        this.nquaFlag = nquaFlag;
    }

    public Boolean getNdealFlag() {
        return ndealFlag;
    }

    public void setNdealFlag(Boolean ndealFlag) {
        this.ndealFlag = ndealFlag;
    }

    @Override
    public String toString() {
        return "LogicalShow{" +
                "stockFlag=" + stockFlag +
                ", quaFlag=" + quaFlag +
                ", actionFlag=" + actionFlag +
                ", vexpFlag=" + vexpFlag +
                ", levelBFlag=" + levelBFlag +
                ", levelCFlag=" + levelCFlag +
                ", levelDFlag=" + levelDFlag +
                ", threeFlag=" + threeFlag +
                ", expFlag=" + expFlag +
                ", checkFlag=" + checkFlag +
                ", nquaFlag=" + nquaFlag +
                ", ndealFlag=" + ndealFlag +
                '}';
    }
}
