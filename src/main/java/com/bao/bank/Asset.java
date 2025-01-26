package com.bao.bank;

/**
 * Asset. Represents an asset in the account.
 */
public interface Asset {
  /**
   * Asset type.
   */
  public enum AssetType {
    /**
     * Cash asset.
     */
    CASH,

    /**
     * Stock asset.
     */
    STOCK,

    /**
     * Bonds asset.
     */
    BONDS,

    /**
     * Mutual fund asset.
     */
    MUTUAL_FUND,

    /**
     * Real estate asset.
     */
    REAL_ESTATE,

    /**
     * Gold asset.
     */
    GOLD
  }

  /**
   * Get asset type.
   * @return asset type
   */
  public AssetType getType();

  /**
   * Get asset balance.
   * @return asset balance
   */
  public double getBalance();

  /**
   * Add asset to the account.
   * @param asset: asset to add
   */
  public void add(Asset asset);

  /**
   * Minus asset from the account.
   * @param asset: asset to minus
   */
  public void minus(Asset asset);
}
