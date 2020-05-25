package com.ui;

import com.factory.BeanFactory;
import com.impl.AccountServiceImpl;
import com.service.IAccountService;

/*
由于没有浏览器存在，这里模拟一个表现层,用于调用业务层
 */

/**
 *  每次按同样的名称取bean对象，默认获得一个重新创建的bean对象
 *  多例：
 *      对象会被创建多次，执行效率没有单例对象高
 *  单例：
 *      对象只被创建一次，从而类中成员也就只被初始化一次
 */
public class client {
    public static void main(String[] args) {
        //IAccountService as = new AccountServiceImpl();
        // service的单例/多例测试
        for(int i=0;i<5;++i){
            IAccountService as = (IAccountService)BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveAccount();

        }


    }

}
