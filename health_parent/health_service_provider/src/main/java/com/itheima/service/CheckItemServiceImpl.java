package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *检查项服务
 */

@Service(interfaceClass = CheckItemService.class)  //加了事务注解后必须明确使用哪个服务接口
@Transactional
public class CheckItemServiceImpl implements CheckItemService{

    @Autowired
    CheckItemDao  checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.queryByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delteById(int id) {
        //确认没有关联的检查组的数据
        long count = checkItemDao.getCountById(id);
        if(count > 0) {
            new RuntimeException("当前检查项有关联的检查组，无法删除");
        } else {
            checkItemDao.delteById(id);
        }
    }

    @Override
    public CheckItem queryById(int id) {
        CheckItem checkItem = checkItemDao.queryById(id);
        return checkItem;
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
