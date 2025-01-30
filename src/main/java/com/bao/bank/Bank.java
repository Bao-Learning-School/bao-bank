package com.bao.bank;

import com.bao.bank.Account.AccountType;
import java.util.ArrayList;
import java.util.List;

public class Bank {
  private final int id;
  private final String name;
  private final String address;
  private final String phone;
  private List<Account> accounts;

  /**
   * Constructor
   *
   * @param id: Bank id
   * @param name: Bank name
   * @param address: Bank address
   * @param phone: Bank phone number
   */
  public Bank(int id, String name, String address, String phone) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.accounts = new ArrayList<Account>();
  }

  /**
   * Open account in the bank.
   *
   * @param accountId: account id
   * @param type: account type
   * @param accountName: account holder name
   * @param accountAddress: account holder address
   * @param accountPhone: account holder phone number
   */
  public void openAccount(
      int accountId,
      AccountType type,
      String accountName,
      String accountAddress,
      String accountPhone) {
    accounts.add(new Account(accountId, type, accountName, accountAddress, accountPhone));
  }

  /**
   * close account from the bank.
   *
   * @param accountId: id of the account to close
   */
  public void closeAccount(int accountId) {
    accounts.removeIf(account -> account.getId() == accountId);
  }

  /**
   * close account from the bank.
   *
   * @param accountName: name of the account to close
   */
  public void closeAccount(String accountName) {
    accounts.removeIf(account -> account.getName().equals(accountName));
  }

  /**
   * Get account by id.
   *
   * @param accountId: id of the account to get
   * @return account with the given id
   */
  public Account getAccount(int accountId) {
    return accounts.stream()
        .filter(account -> account.getId() == accountId)
        .findFirst()
        .orElse(null);
  }

  /**
   * Get account by name.
   *
   * @param accountName: name of the account to get
   * @return account with the given name
   */
  public Account getAccount(String accountName) {
    return accounts.stream()
        .filter(account -> account.getName().equals(accountName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Get string representation of the bank.
   *
   * @return string representation of the bank
   */
  @Override
  public String toString() {
    return String.format("Bank{id:%d, name:%s, address:%s, phone:%s}", id, name, address, phone);
  }

  public static void main(String[] args) {
    Bank bank = new Bank(1, "Bank of America", "Charlotte, NC", "1-800-432-1000");
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    bank.openAccount(2, AccountType.SUPER_VIP, "Bob", "456 Elm St", "234-567-8901");
    bank.openAccount(3, AccountType.BUSINESS, "Charlie", "789 Oak St", "345-678-9012");
    bank.closeAccount(2);
    bank.closeAccount("Charlie");
    System.out.println(bank);
  }
}
