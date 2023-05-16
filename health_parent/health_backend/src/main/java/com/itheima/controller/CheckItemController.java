package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Student;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    CheckItemService checkItemService;

    @GetMapping("/getStudent")
    public Result getStudent(){
        Student student = new Student();
        student.setName("zs");
        student.setDate(new Date());
        student.setTimestamp(new Timestamp(1663171200000L));
        List<Object> list = new ArrayList<>();
        list.add(student.getName());
        list.add(student.getDate());
        list.add(student.getTimestamp());
       return  new Result(true,"aaa",list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @PostMapping("/pageQuery")
    public PageResult pageQuery(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }

    @GetMapping("/delete")
    public Result delete(int id){
        try {
            checkItemService.delteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("/queryById")
    public Result queryById(int id) {
        CheckItem checkItem = null;
        try {
            checkItem = checkItemService.queryById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true,"",checkItem);
    }

    @PostMapping("/update")
    public Result updata(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll() {
        try{
            List<CheckItem> checkItems = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL,null);
        }
    }
}
