package com.bao.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BondsTest {

  @Test
  public void testConstructor() {
    Bonds bonds = new Bonds(1000.0);
    assertEquals(1000.0, bonds.getBalance());
  }

  @Test
  public void testGetType() {
    Bonds bonds = new Bonds(1000.0);
    assertEquals(Asset.AssetType.BONDS, bonds.getType());
  }

  @Test
  public void testGetBalance() {
    Bonds bonds = new Bonds(1000.0);
    assertEquals(1000.0, bonds.getBalance());
  }

  @Test
  public void testSetBalance() {
    Bonds bonds = new Bonds(1000.0);
    bonds.setBalance(2000.0);
    assertEquals(2000.0, bonds.getBalance());
  }

  @Test
  public void testAdd() {
    Bonds bonds1 = new Bonds(1000.0);
    Bonds bonds2 = new Bonds(500.0);
    bonds1.add(bonds2);
    assertEquals(1500.0, bonds1.getBalance());
  }

  @Test
  public void testAddNonBondsAsset() {
    Bonds bonds = new Bonds(1000.0);
    Asset nonBonds = new Asset() {
      @Override
      public AssetType getType() {
        return Asset.AssetType.STOCK;
      }

      @Override
      public double getBalance() {
        return 500.0;
      }

      @Override
      public void add(Asset asset) {}

      @Override
      public void minus(Asset asset) {}
    };

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      bonds.add(nonBonds);
    });
    
    assertEquals(exception.getMessage(), "Cannot add STOCK asset to bonds asset");

  }

  @Test
  public void testMinus() {
    Bonds bonds1 = new Bonds(1000.0);
    Bonds bonds2 = new Bonds(500.0);
    bonds1.minus(bonds2);
    assertEquals(500.0, bonds1.getBalance());
  }

  @Test
  public void testMinusNonBondsAsset() {
    Bonds bonds = new Bonds(1000.0);
    Asset nonBondsAsset = new Asset() {
      @Override
      public AssetType getType() {
        return AssetType.STOCK;
      }

      @Override
      public double getBalance() {
        return 500.0;
      }

      @Override
      public void add(Asset asset) {}

      @Override
      public void minus(Asset asset) {}
    };

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      bonds.minus(nonBondsAsset);
    });

    String expectedMessage = "Cannot minus non-cash asset from cash asset";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testMinusInsufficientBalance() {
    Bonds bonds1 = new Bonds(500.0);
    Bonds bonds2 = new Bonds(1000.0);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      bonds1.minus(bonds2);
    });

    String expectedMessage = "Insufficient bonds, amount to minus $1000.00, current balance is $500.00";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testToString() {
    Bonds bonds = new Bonds(1000.0);
    assertEquals("Bonds: 1000.00", bonds.toString());
  }
}