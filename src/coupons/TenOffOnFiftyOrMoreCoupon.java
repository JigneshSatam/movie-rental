package coupons;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class TenOffOnFiftyOrMoreCoupon extends BaseCoupon {

  double DECIDING_FACTOR = 50.0;
  double AMOUNT_OFF = 10.0;

  public TenOffOnFiftyOrMoreCoupon(PriceCaclulator coupon) {
    super(coupon);
  }

  @Override
  public double evaluateCost() {
    double rental = super.evaluateCost();
    if (rental >= DECIDING_FACTOR) {
      rental -= AMOUNT_OFF;
    }
    return rental;
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = super.details();;
    double rental = super.evaluateCost();
    if (rental >= DECIDING_FACTOR) {
      priceDetails.add(new PriceDetail("$10 off", AMOUNT_OFF * -1));
    }
    return priceDetails;
  }
}
