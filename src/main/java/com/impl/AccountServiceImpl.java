package com.impl;

import com.dao.IAccountDao;
import com.factory.BeanFactory;
import com.service.IAccountService;
/*
账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    // 业务层实现类私有属性赋值比静态代码块快导致空指针异常
    // 解决方案 将该变量的声明放到类内部

    // Spring Ioc的思想 类把对象创建的权利交给了bean工厂，由创建对象变成申请对象
    // 降低依赖关系 削减耦合
    // 工厂制造出来的bean对象是可以复用的
    //private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
    //private IAccountDao accountDao = new AccountDaoImpl();

    /**
     * 方法内与方法外的变量i
     * 验证了单例对象本身只被创建一次，但对象内部的方法会在每次调用时候重新初始化
     */

    private int i = 1;
    public void saveAccount(){
        IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
        System.out.println(accountDao);
        // 验证生成的bean对象的方法会重新初始化
        // 多例对象没有线程安全问题
        //int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        ++i;
    }

}
