package rentals.pricing;

public class RegularPricingStrategy implements PricingStrategy {
  @Override
  public double calculatePrice(int numberOfDaysRented) {
    double rental = 2;
    if (numberOfDaysRented > 2) {
      rental += (numberOfDaysRented - 2) * 1.5;
    }
    return rental;
  }
}
