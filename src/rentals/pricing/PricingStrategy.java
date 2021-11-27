package rentals.pricing;

public interface PricingStrategy {
  double calculatePrice(int numberOfDaysRented);
}
