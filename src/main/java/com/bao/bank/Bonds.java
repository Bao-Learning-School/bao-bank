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
   * Get asset type.
   * @return asset type
   */
  @Override
  public AssetType getType() {
    return AssetType.BONDS;
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
   * Add bonds asset to the account.
   * @param asset: bonds asset to add
   * @throws IllegalArgumentException if asset is not bonds asset
   */
  @Override
  public void add(Asset asset) throws IllegalArgumentException {
    if (asset.getType() != AssetType.BONDS) {
      throw new IllegalArgumentException(
        String.format("Cannot add %s asset to bonds asset",
          asset.getType()));
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
    if (asset.getType() != AssetType.BONDS) {
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
