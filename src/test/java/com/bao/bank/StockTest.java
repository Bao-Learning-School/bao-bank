package com.bao.bank;

import com.bao.bank.Asset;
import com.bao.bank.Stock;
import org.junit.Test;
import org.junit.Assert;

public class StockTest {
  @Test
  public void testGetType() {
    Stock stock = new Stock("AAPL", 100, 100.0);
    Assert.assertEquals(Asset.AssetType.STOCK, stock.getType());
  }

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
}
