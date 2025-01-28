package com.bao.bank;

import java.util.List;

public class Bank {
  // - id : int
  // - name : String
  // - address: String
  // - accounts: List<Account>
  // + add( Account account )
  // + remove(Accounta account)
  // + getAccount(int id)
  // + getAccount(String owner_name)

  private int id;
  private String name;
  private String address;
  private List<Account> accounts;
}