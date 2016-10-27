package com.jtthink;

import com.Utils.BuyNowData;
import com.Utils.ZkUtil;
import com.db.Mapper.ProdEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tinyhome on 2016/10/12.
 */

@Controller
public class TestController {
    @RequestMapping(value = "/tiny",method = RequestMethod.GET)
    public void index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception
    {
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("192.168.8.223:2181," +
                                                  "192.168.8.224:2181," +
                                                  "192.168.8.225:2181", new RetryNTimes(2, 5000));
        client.start();

        String path = httpServletRequest.getParameter("p");
        byte[] result = client.getData().forPath("/" + path);
        httpServletResponse.getWriter().write(new String(result));


        client.close();
    }

    @Autowired
    ZkUtil zkUtil;

    @Autowired
    BuyNowData buyNowData;

    @RequestMapping(value = "/sys", method = RequestMethod.GET)
    public void sys(HttpServletRequest rq,HttpServletResponse rs) throws Exception {

        //下面代码为 后台可以用来 进行zookeeper 商品库存的写入
        List<ProdEntity> prodEntities=new ArrayList<ProdEntity>();

        ProdEntity prod1=new ProdEntity();
        prod1.setProdId(101);
        prod1.setProdNum(100);
        prod1.setProdType(1);//1代表图书


        ProdEntity prod2=new ProdEntity();
        prod2.setProdId(102);
        prod2.setProdNum(50);
        prod2.setProdType(1);//1代表图书

        prodEntities.add(prod1);
        prodEntities.add(prod2);

        ObjectMapper objectMapper=new ObjectMapper();
        zkUtil.SetPathData("/buynow/20161024/book",objectMapper.writeValueAsString(prodEntities));

    }

    //用来显示数据
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public  void book(HttpServletRequest rq,HttpServletResponse rs) throws IOException {
        rs.setContentType("text/html; charset=utf-8");
        if(buyNowData.prodEntityList!=null)
        {
            for(ProdEntity prodEntity : buyNowData.prodEntityList)
            {
                rs.getWriter().write("商品id为"+prodEntity.getProdId()+"的商品还有 "
                        +String.valueOf(prodEntity.getProdNum())+"件</br>");
            }
        }
        else
        {
            rs.getWriter().write("暂无秒杀数据");
        }
    }

    //用来演示监听节点
    @RequestMapping(value = "/watch", method = RequestMethod.GET)
    public  void watch(HttpServletRequest rq,HttpServletResponse rs) throws Exception {
        rs.setContentType("text/html; charset=utf-8");
        if(zkUtil.watchBook())
        {
            rs.getWriter().write("图书节点开始监听");
        }
        else
        {
            rs.getWriter().write("图书节点秒杀还没数据，无法监听");
        }
    }


}
