package coupon;

import pricing.PriceCaclulator;

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
}
