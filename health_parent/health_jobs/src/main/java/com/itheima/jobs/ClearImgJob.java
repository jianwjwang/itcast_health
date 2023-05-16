package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(sdiff != null) {
            for(String key:sdiff) {
                QiniuUtils.deleteFileFromQiniu(key);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,key);
                System.out.println("----------clear picture "+key);
            }
        }
    }
}
