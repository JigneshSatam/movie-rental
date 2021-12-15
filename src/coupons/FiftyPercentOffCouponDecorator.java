package coupons;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class FiftyPercentOffCouponDecorator extends BaseCouponDecorator {

  double FIFTY_PERCENT = 50 / 100.0;

  public FiftyPercentOffCouponDecorator(PriceCaclulator coupon) {
    super(coupon);
  }

  @Override
  public double evaluateCost() {
    return super.evaluateCost() * FIFTY_PERCENT;
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = super.details();
    priceDetails.add(new PriceDetail("50% off", evaluateCost() * -1));
    return priceDetails;
  }
}
