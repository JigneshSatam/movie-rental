package coupons;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class BaseCoupon implements PriceCaclulator {
  private PriceCaclulator _priceCaclulator;

  public BaseCoupon(PriceCaclulator priceCaclulator) {
    this._priceCaclulator = priceCaclulator;
  }

  @Override
  public double evaluateCost() {
    return _priceCaclulator.evaluateCost();
  }

  @Override
  public ArrayList<PriceDetail> details() {
    return _priceCaclulator.details();
  }
}
