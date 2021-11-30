package rentals.pricingStrategies;

public class RegularPricingStrategy implements RentalPricingStrategy {
  @Override
  public double calculatePrice(int numberOfDaysRented) {
    double rental = 2;
    if (numberOfDaysRented > 2) {
      rental += (numberOfDaysRented - 2) * 1.5;
    }
    return rental;
  }
}
