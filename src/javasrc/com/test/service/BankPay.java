package com.test.service;

/**
 * Created by Tinyhome on 16/7/14.
 */
public class BankPay implements PayMethod {
    //最早的银行卡支付
    public String payMoney() {
        return "使用了第三方网银接口支付!";
    }


}
