package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    Page<CheckItem> queryByCondition(String queryString);

    CheckGroup queryById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void edit(CheckGroup checkGroup);

    void deleteRelatedCheckItem(int id);

    List<CheckGroup> findAll();
}
