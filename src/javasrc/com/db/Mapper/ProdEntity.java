package com.db.Mapper;

/**
 * Created by Tinyhome on 2016/10/27.
 */
public class ProdEntity {
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getProdNum() {
        return prodNum;
    }

    public void setProdNum(int prodNum) {
        this.prodNum = prodNum;
    }

    public int getProdType() {
        return prodType;
    }

    public void setProdType(int prodType) {
        this.prodType = prodType;
    }

    private int prodId;
    private int prodNum;
    private int prodType;
}
