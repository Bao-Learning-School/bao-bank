package com.bao.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AccountTest {
  private Account account;

  @BeforeEach
  public void setUp() {
    account = new Account(1,
                          Account.AccountType.PERSONAL,
                          "John Doe",
                          "123 Main St",
                          "555-1234");
  }

  @Test
  public void testAccountCreation() {
    assertEquals(1, account.getId());
    assertEquals(Account.AccountType.PERSONAL, account.getType());
    assertEquals("John Doe", account.getName());
    assertEquals("123 Main St", account.getAddress());
    assertEquals("555-1234", account.getPhone());
  }

  @Test
  public void testGetBalance() {
    Asset cash = new Cash(100.0);
    Asset stock = new Stock("GOOG", 200, 1500.0);
    account.deposit(cash);
    account.deposit(stock);
    assertEquals(300100.0, account.getBalance(), 0.01);
  }

  @Test
  public void testDeposit() {
    Asset asset = new Cash(100.0);
    account.deposit(asset);
    List<Asset> assets = account.getAssets();
    assertEquals(1, assets.size());
    assertEquals(asset, assets.get(0));
  }

  @Test
  public void testWithdraw() {
    Asset asset = new Cash(100.0);
    account.deposit(asset);
    account.withdraw( new Cash(50.0));
    assertEquals(50.0, account.getBalance(), 0.01);
  }

  @Test
  public void testWithdrawInsufficientBalance() {
    Asset asset = new Cash(100.0);
    account.deposit(asset);
    Error error = assertThrows(Error.class, () -> {
      account.withdraw(new Cash(150.0));
    });

    assertEquals("Insufficient balance of class com.bao.bank.Cash, amount to " +
                 "withdraw $150.00, current balance is $100.00",
                 error.getMessage());
  }

  @Test
  public void testRemoveAllAsset() {
    Asset asset1 = new Cash(100.0);
    Asset asset2 = new Cash(200.0);
    account.deposit(asset1);
    account.deposit(asset2);
    account.removeAllAsset();
    assertEquals(0, account.getAssets().size());
  }

  @Test
  public void testWithdrawDifferentAsset() {
    Asset asset = new Cash(100.0);
    account.deposit(asset);
    Error error = assertThrows(Error.class, () -> {
      account.withdraw(new Bonds(150));
    });
    assertEquals("Asset class com.bao.bank.Bonds not found", error.getMessage());
  }
}