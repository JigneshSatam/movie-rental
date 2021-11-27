package coupon;

import pricing.PriceCaclulator;

public class BaseCoupon implements PriceCaclulator {
  private PriceCaclulator _priceCaclulator;

  public BaseCoupon(PriceCaclulator priceCaclulator) {
    this._priceCaclulator = priceCaclulator;
  }

  @Override
  public double evaluateCost() {
    return _priceCaclulator.evaluateCost();
  }
}
