package rentals.pricingStrategies;

public class ChildrenPricingStrategy implements RentalPricingStrategy {
  @Override
  public double calculatePrice(int numberOfDaysRented) {
    double rental = 1.5;
    if (numberOfDaysRented > 3) {
      rental += (numberOfDaysRented - 3) * 1.5;
    }
    return rental;
  }
}
