package com.impl;

import com.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {
    public void saveAccount(){
        System.out.println("保存了账户");
    }
}
