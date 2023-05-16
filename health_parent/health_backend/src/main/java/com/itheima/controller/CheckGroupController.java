package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    CheckGroupService checkGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds) {
        try
        {
            checkGroupService.add(checkGroup,checkitemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findPage(queryPageBean);
    }

    @GetMapping("/findById")
    public Result findById(int id) {
        try{
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupService.findById(id));
        }catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id) {
        try{
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkGroupService.findCheckItemIdsByCheckGroupId(id));
        }catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds) {
        try{
            checkGroupService.edit(checkGroup, checkitemIds);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/findAll")
    public Result findAll() {
        try{
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkGroupService.findAll());
        }catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
