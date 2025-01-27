package com.bao.bank;

import java.util.List;
import java.util.ArrayList;

/**
 * Account. Represents a bank account.
 */
public class Account {
  /**
   * Account type.
   */
  public enum AccountType {
    /**
     * Personal account.
     */
    PERSONAL,

    /**
     * Business account.
     */
    BUSINESS,
    
    /**
     * Private client account.
     */
    PRIVATE_CIIENT,
    
    /**
     * VIP account. And the account holder is a Super-duper VIP.
     */
    SUPER_VIP
  }

  private final int id;
  private final AccountType type;
  private final String name;
  private final String address;
  private final String phone;
  private ArrayList<Asset> assets;

  /**
   * Constructor
   * @param id: account id
   * @param type: account type
   * @param name: account holder name
   * @param address: account holder address
   * @param phone: account holder phone number
   */
  public Account(int id,
                 AccountType type,
                 String name,
                 String address,
                 String phone) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.assets = new ArrayList<Asset>();
  }

  /**
   * Get account id.
   * @return account id
   */
  public int getId() {
    return id;
  }

  /**
   * Get account type.
   * @return account type
   */
  public AccountType getType() {
    return type;
  }

  /**
   * Get account holder name.
   * @return account holder name
   */
  public String getName() {
    return name;
  }

  /**
   * Get account holder address.
   * @return account holder address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Get account holder phone number.
   * @return account holder phone number
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Get account assets.
   * @return account assets
   */
  public List<Asset> getAssets() {
    return assets;
  }

  /**
   * Get account balance.
   * @return account balance
   */
  public double getBalance() {
    double balance = 0.0;
    for (Asset asset : assets) {
      balance += asset.getBalance();
    }
    return balance;
  }

  /**
   * Add an asset to the account.
   * @param asset: asset to add
   */
  public void deposit(Asset asset) {
    for (Asset a : assets) {
      if (a.getType() == asset.getType()) {
        a.add(asset);
        return;
      }
    }
    assets.add(asset);
  }

  /**
   * Remove some asset from the account.
   * @param asset_to_withdraw: asset to withdraw from the account.
   * @throws Error if the asset is not found or the balance is insufficient.
   */
  public void withdraw(Asset asset_to_withdraw) {
    for (Asset asset : assets) {
      if (asset.getType() == asset_to_withdraw.getType()) {
        if (asset.getBalance() < asset_to_withdraw.getBalance()) {
          throw new Error(String.format(
            "Insufficient balance of %s, amount to withdraw $%.2f, current balance is $%.2f",
            asset.getType(), asset_to_withdraw.getBalance(), asset.getBalance()));
        } else if (asset.getBalance() == asset_to_withdraw.getBalance()) {
          assets.remove(asset);
        } else {
          asset.minus(asset_to_withdraw);
        }
        return;
      }
    }
    throw new Error(String.format("Asset %s not found", asset_to_withdraw.getType()));
  } 

  /**
   * Remove all assets from the account.
   */
  public void removeAllAsset() {
    assets.clear();
  }
}
