package coupon;

import pricing.PriceCaclulator;

public class CouponFactory {

  public PriceCaclulator getCoupon(CouponType type, PriceCaclulator priceCaclulator) {
    switch (type) {
    case FIFTY_PERCENT_OFF:
      return new FiftyPercentOffCoupon(priceCaclulator);

    case TEN_OFF_ON_FIFTY_OR_MORE_COUPON:
    default:
      return new TenOffOnFiftyOrMoreCoupon(priceCaclulator);
    }
  }

}
