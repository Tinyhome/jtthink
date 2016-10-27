package com.jtthink;

import com.Utils.TokenCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tinyhome on 16/9/8.
 */
@RestController
public class NewsController {

    @TokenCheck
    @RequestMapping(value = "/news",method = RequestMethod.GET)
    public String getNews()
    {
        return "this is test News";
    }

    @RequestMapping(value = "/newstest",method = RequestMethod.GET)
    public String newstest() throws Exception {
        throw new Exception("newtest method ");
        //return "no token"; //测试API,代表不需要token验证
    }



}
