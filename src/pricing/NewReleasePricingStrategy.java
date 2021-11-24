package pricing;

public class NewReleasePricingStrategy implements PricingStrategy {
	@Override
	public double calculatePrice(int numberOfDaysRented) { 
		return numberOfDaysRented * 3;
	}
}