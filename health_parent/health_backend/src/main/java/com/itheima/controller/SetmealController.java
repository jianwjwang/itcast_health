package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) throws IOException {
        String fileName = imgFile.getOriginalFilename();
        int lastIndexOf = fileName.lastIndexOf(".");
        String suffix = fileName.substring(lastIndexOf - 1);
        String newFilename = UUID.randomUUID().toString() + suffix;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),newFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
        }
        try {
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFilename);
        } catch (Exception e) {
            e.printStackTrace();
            //删除七牛存储的图片
            QiniuUtils.deleteFileFromQiniu(newFilename);
            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);

        /*try {
            String fileName = imgFile.getOriginalFilename();
            int lastIndexOf = fileName.lastIndexOf(".");
            String suffix = fileName.substring(lastIndexOf - 1);
            String newFilename = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),newFilename);
            //将图片名称存入redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFilename);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,newFilename);
        } catch (IOException e) {
            e.printStackTrace();
            //如果是七牛存储失败 不用处理
            //如果是存入redis失败，需删除已经存入七牛的文件
            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
        }*/
    }

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try
        {
            setmealService.add(setmeal,checkgroupIds);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.findPage(queryPageBean);
    }
}
