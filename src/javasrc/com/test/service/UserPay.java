package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tinyhome on 16/7/13.
 */
public class UserPay {
    //用户支付类,包括余额支付、在线支付和赠送虚拟币支付

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    private HttpServletResponse response;
    private PayMethod payMethod;

    //在线支付
    public void pay() throws IOException {
        response.getWriter().write(payMethod.payMoney());

    }

}
