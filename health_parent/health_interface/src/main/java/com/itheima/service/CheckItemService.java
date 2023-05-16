package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void delteById(int id);

    CheckItem queryById(int id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}
