package com.bao.bank;

import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.Test;

public class StockTest {
  @Test
  public void testGetBalance() {
    Stock stock = new Stock("AAPL", 100, 100.0);
    Assert.assertEquals(stock.getBalance(), 10000.0, 0.01);
  }

  @Test
  public void testGetTicker() {
    Stock stock = new Stock("AAPL", 100, 100.0);
    Assert.assertEquals(stock.getTicker(), "AAPL");
  }

  @Test
  public void testGetNewShares() {
    Stock stock = new Stock("AAPL", 100, 100.0);
    Assert.assertEquals(stock.getNumShares(), 100);
  }

  @Test
  public void testGetPricePerShare() {
    Stock stock = new Stock("AAPL", 100, 100.0);
    Assert.assertEquals(stock.getPricePerShare(), 100, 0.01);
  }

  @Test
  public void testAdd() {
    Stock stock1 = new Stock("AAPL", 100, 100.0);
    Stock stock2 = new Stock("AAPL", 50, 100.0);
    stock1.add(stock2);
    Assert.assertEquals(stock1.getNumShares(), 150);
  }

  @Test
  public void testMinus() {
    Stock stock1 = new Stock("AAPL", 100, 100.0);
    Stock stock2 = new Stock("AAPL", 50, 100.0);
    stock1.minus(stock2);
    Assert.assertEquals(stock1.getNumShares(), 50);
  }

  @Test
  public void testMinus_InsufficentFunds() {
    Stock stock1 = new Stock("AAPL", 100, 100.0);
    Stock stock2 = new Stock("AAPL", 500, 100.0);
    IllegalArgumentException error =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              stock1.minus(stock2);
            });

    Assert.assertEquals(
        error.getMessage(),
        "Insufficient number of shares of AAPL, "
            + "number of shares to minus 500, current number of shares is 100");
  }
}
