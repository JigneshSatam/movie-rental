package coupons;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class BaseCouponDecorator implements PriceCaclulator {
  private PriceCaclulator _priceCaclulator;

  public BaseCouponDecorator(PriceCaclulator priceCaclulator) {
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
