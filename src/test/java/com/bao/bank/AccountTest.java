package com.bao.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
  private Account account;

  @BeforeEach
  public void setUp() {
    account = new Account(1, Account.AccountType.PERSONAL, "John Doe", "123 Main St", "555-1234");
  }

  @Test
  public void testDepositDifferentAssets() {
    Asset cash = new Cash(100.0);
    Asset stock1 = new Stock("GOOG", 200, 1500.0);
    Asset stock2 = new Stock("AAPL", 100, 100.0);
    Asset bonds = new Bonds(1000.0);
    account.deposit(cash);
    account.deposit(stock1);
    account.deposit(stock2);
    account.deposit(bonds);
    account.deposit(new Cash(150.0));
    List<Asset> assets = account.getAssets();
    assertEquals(4, assets.size());
    assertEquals(cash, assets.get(0));
    assertEquals(stock1, assets.get(1));
    assertEquals(stock2, assets.get(2));
    assertEquals(bonds, assets.get(3));
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
    account.withdraw(new Cash(50.0));
    assertEquals(50.0, account.getBalance(), 0.01);
  }

  @Test
  public void testWithdrawInsufficientBalance() {
    Asset asset = new Cash(100.0);
    account.deposit(asset);
    Error error =
        assertThrows(
            Error.class,
            () -> {
              account.withdraw(new Cash(150.0));
            });

    assertEquals(
        "Insufficient balance: cannot withdraw (Cash: $150.00) from (Cash: $100.00)",
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
  public void testWithdrawDifferentAssets() {
    Asset stock1 = new Stock("GOOG", 200, 1500.0);
    Asset stock2 = new Stock("AAPL", 100, 100.0);
    Asset bonds = new Bonds(1000.0);
    account.deposit(stock1);
    account.deposit(stock2);
    account.deposit(bonds);
    account.withdraw(new Stock("GOOG", 100, 1500.0));
    account.withdraw(new Stock("AAPL", 50, 100.0));
    account.withdraw(new Bonds(500.0));

    Error error =
        assertThrows(
            Error.class,
            () -> {
              account.withdraw(new Cash(150.0));
            });
    assertEquals("Asset (Cash: $150.00) not found", error.getMessage());

    Error error2 =
        assertThrows(
            Error.class,
            () -> {
              account.withdraw(new Stock("GOOG", 300, 1500.0));
            });
    assertEquals(
        "Insufficient balance: cannot withdraw (Stock: GOOG x 300 @ $1500.00) "
            + "from (Stock: GOOG x 100 @ $1500.00)",
        error2.getMessage());
  }
}
