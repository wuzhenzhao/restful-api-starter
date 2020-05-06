package com.wuzz.rest.test;

import com.wuzz.rest.annotation.ApiDoc;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/23
 * Time: 11:05
 * Description 描述:
 */
public class Account {
    @ApiDoc("账户名")
    private String accountName;

    @ApiDoc("密码")
    private String password;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
