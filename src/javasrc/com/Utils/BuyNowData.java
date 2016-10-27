package com.Utils;

import com.db.Mapper.ProdEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Tinyhome on 2016/10/27.
 */
public class BuyNowData {


    ZkUtil zkUtil;

    public List<ProdEntity> prodEntityList=null;


    public  BuyNowData(ZkUtil _zkutil)
    {
        zkUtil=_zkutil;
    }


    public void initProdlist() throws Exception {

        //容器启动时，自动从 zookeeper中获取数据
        String getData=zkUtil.getPathData("/buynow/20161024/book");
        if(getData!="")
        {
            ObjectMapper objectMapper=new ObjectMapper();

            //请注意 下面语句中的 类型转换
            this.prodEntityList=objectMapper.readValue(getData, new TypeReference<List<ProdEntity>>() {});
        }
        else
            this.prodEntityList=null;
    }
}
