package com.bao.bank;

/**
 * Cash asset. Represents cash in the account. One kind of asset.
 */
public class Cash implements Asset {
  private double balance;

  /**
   * Constructor
   * @param initial_balance: Initial cash balance
   */
  public Cash(double initial_balance) {
    this.balance = initial_balance;
  }

  /**
   * Get cash balance.
   * @return cash balance
   */
  @Override
  public double getBalance() {
    return balance;
  }

  /**
   * Set cash balance.
   * @param balance: new cash balance
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Check if  is cash ompatible with another asset for addition and subtraction.
   * @param asset: asset to check compatibility with
   * @return true if compatible, false otherwise
   */
  @Override
  public boolean isCompatible(Asset asset) {
    return asset instanceof Cash;
  }

  /**
   * Add cash asset to the account.
   * @param asset: cash asset to add
   * @throws IllegalArgumentException if asset is not cash asset
   */
  @Override
  public void add(Asset asset) throws IllegalArgumentException {
    if (!isCompatible(asset)) {
      throw new IllegalArgumentException(
        String.format("Cannot add %s asset to cash asset",
          asset.getClass()));
    }

    balance += asset.getBalance();
  }

  /**
   * Minus cash asset from the account.
   * @param asset: cash asset to minus
   * @throws IllegalArgumentException if asset is not cash asset
   */
  @Override
  public void minus(Asset asset) throws IllegalArgumentException {
    if (!isCompatible(asset)) {
      throw new IllegalArgumentException("Cannot minus non-cash asset from cash asset");
    }
    balance -= asset.getBalance();
  }
  
  /**
   * Get cash asset as string.
   * @return cash asset as string
   */
  @Override
  public String toString() {
    return String.format("Cash: %.2f", balance);
  }
}
