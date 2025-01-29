package com.bao.bank;

/**
 * Stock asset. Represents stock in the account. One kind of asset.
 */
public class Stock implements Asset {
  /**
   * Stock ticker.
   */
  private String ticker;

  /**
   * Number of shares.
   */
  private int numShares;

  /**
   * Price per share.
   */
  private double pricePerShare;

  /**
   * Constructor.
   * 
   * @param ticker:       Stock ticker
   * @param numShares     Number of shares
   * @param pricePerShare Price per share
   */
  public Stock(String ticker, int numShares, double pricePerShare) {
    this.ticker = ticker;
    this.numShares = numShares;
    this.pricePerShare = pricePerShare;
  }

  public String toString() {
    return String.format("(Stock: %s x %d @ $%.2f)",
        ticker, numShares, pricePerShare);
  }

  /**
   * Get stock ticker.
   * 
   * @return stock ticker.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Get number of shares.
   * 
   * @return number of shares.
   */
  public int getNumShares() {
    return numShares;
  }

  /**
   * Get price per share.
   * 
   * @return price per share.
   */
  public double getPricePerShare() {
    return pricePerShare;
  }

  /**
   * Get asset balance.
   * 
   * @return asset balance.
   */
  @Override
  public double getBalance() {
    return numShares * pricePerShare;
  }

  /**
   * Check if Stock is compatible with another asset for addition and subtraction.
   * 
   * @param asset: asset to check compatibility with
   * @return true if compatible, false otherwise
   */
  @Override
  public boolean isCompatible(Asset asset) {
    if (!(asset instanceof Stock)) {
      return false;
    }
    Stock stock = (Stock) asset;
    return stock.getTicker().equals(ticker);
  }

  /**
   * Add stock asset to the account.
   * 
   * @param asset: stock asset to add
   * @throws IllegalArgumentException if asset is not stock asset
   * @throws IllegalArgumentException if stock tickers are different
   */
  @Override
  public void add(Asset asset) throws IllegalArgumentException {
    if (!(asset instanceof Stock)) {
      throw new IllegalArgumentException(
          String.format("Cannot add %s asset to stock asset",
              asset));
    }

    Stock stock = (Stock) asset;
    if (!stock.getTicker().equals(ticker)) {
      throw new IllegalArgumentException(
          String.format("Cannot add stock with different ticker %s to stock %s",
              stock.getTicker(), ticker));
    }

    pricePerShare = (getBalance() + stock.getBalance()) /
        (numShares + stock.getNumShares());
    numShares += stock.getNumShares();
  }

  /**
   * Minus stock asset from the account.
   * 
   * @param asset: stock asset to minus
   * @throws IllegalArgumentException if asset is not stock asset
   * @throws IllegalArgumentException if stock tickers are different
   * @throws IllegalArgumentException if stock balance is insufficient
   */
  @Override
  public void minus(Asset asset) throws IllegalArgumentException {
    if (!(asset instanceof Stock)) {
      throw new IllegalArgumentException(
          String.format("Cannot minus %s asset from stock asset",
              asset));
    }

    Stock stock = (Stock) asset;
    if (!stock.getTicker().equals(ticker)) {
      throw new IllegalArgumentException(
          String.format("Cannot minus stock with different ticker %s from stock %s",
              stock.getTicker(), ticker));
    }

    if (numShares < stock.getNumShares()) {
      throw new IllegalArgumentException(String.format(
          "Insufficient number of shares of %s, number of shares to minus %d, current number of shares is %d",
          ticker, stock.getNumShares(), numShares));
    } else {
      numShares -= stock.getNumShares();
    }
  }
}
