package com.bao.bank;

import static org.junit.jupiter.api.Assertions.*;

import com.bao.bank.Account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

  private Bank bank;

  @BeforeEach
  public void setUp() {
    bank = new Bank(1, "Bank of America", "Charlotte, NC", "1-800-432-1000");
  }

  @Test
  public void testOpenAccount() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    Account account = bank.getAccount("Alice");
    assertNotNull(account);
    assertEquals("Alice", account.getName());
  }

  @Test
  public void testCloseAccountById() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    bank.closeAccount(1);
    Account account = bank.getAccount(1);
    assertNull(account);
  }

  @Test
  public void testCloseAccountByName() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    bank.closeAccount("Alice");
    Account account = bank.getAccount(1);
    assertNull(account);
  }

  @Test
  public void testGetAccountById() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    Account account = bank.getAccount(1);
    assertNotNull(account);
    assertEquals("Alice", account.getName());
  }

  @Test
  public void testGetAccountByName() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    Account account = bank.getAccount("Alice");
    assertNotNull(account);
    assertEquals(1, account.getId());
  }

  @Test
  public void testOpenMultipleAccounts() {
    bank.openAccount(1, AccountType.PERSONAL, "Alice", "123 Main St", "123-456-7890");
    bank.openAccount(2, AccountType.BUSINESS, "Bob", "456 Elm St", "234-567-8901");
    assertNotNull(bank.getAccount(1));
    assertNotNull(bank.getAccount(2));
  }

  @Test
  public void testCloseNonExistentAccount() {
    bank.closeAccount(999);
    Account account = bank.getAccount(999);
    assertNull(account);
  }
}
