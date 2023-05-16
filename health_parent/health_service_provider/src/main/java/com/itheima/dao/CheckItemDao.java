package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);

    Page<CheckItem> queryByCondition(String queryString);

    long getCountById(int id);

    void delteById(int id);

    CheckItem queryById(int id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}
