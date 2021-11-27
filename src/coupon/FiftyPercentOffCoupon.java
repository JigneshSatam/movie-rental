package coupon;

import pricing.PriceCaclulator;

public class FiftyPercentOffCoupon extends BaseCoupon {

	double FIFTY_PERCENT = 50 / 100.0;

	public FiftyPercentOffCoupon(PriceCaclulator coupon) {
		super(coupon);
	}

	@Override
	public double evaluateCost() {
		double rental = super.evaluateCost();
		return rental * FIFTY_PERCENT;
	}

}
