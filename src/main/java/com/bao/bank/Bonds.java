package com.bao.bank;

/**
 * Bonds asset. Represents bonds in the account. One kind of asset.
 */
public class Bonds implements Asset {
  private double balance;

  /**
   * Constructor
   * @param initial_balance: Initial cash balance
   */
  public Bonds(double initial_balance) {
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
   * Set bonds balance.
   * @param balance: new bonds balance
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Check if bonds is compatible with another asset for addition and subtraction.
   * @param asset: asset to check compatibility with
   * @return true if compatible, false otherwise
   */
  @Override
  public boolean isCompatible(Asset asset) {
    return asset instanceof Bonds;
  }

  /**
   * Add bonds asset to the account.
   * @param asset: bonds asset to add
   * @throws IllegalArgumentException if asset is not bonds asset
   */
  @Override
  public void add(Asset asset) throws IllegalArgumentException {
    if (!isCompatible(asset)) {
      throw new IllegalArgumentException(
        String.format("Cannot add %s asset to bonds asset",
          asset.getClass()));
    }

    balance += asset.getBalance();
  }

  /**
   * Minus bonds asset from the account.
   * @param asset: bonds asset to minus
   * @throws IllegalArgumentException if asset is not bonds asset
   */
  @Override
  public void minus(Asset asset) throws IllegalArgumentException {
    if (!isCompatible(asset)) {
      throw new IllegalArgumentException("Cannot minus non-cash asset from cash asset");
    }

    if (balance < asset.getBalance()) {
      throw new IllegalArgumentException(String.format(
        "Insufficient bonds, amount to minus $%.2f, current balance is $%.2f",
        asset.getBalance(), balance));
    }
    balance -= asset.getBalance();
  }
  
  /**
   * Get bonds asset as string.
   * @return bonds asset as string
   */
  @Override
  public String toString() {
    return String.format("Bonds: %.2f", balance);
  }
}
