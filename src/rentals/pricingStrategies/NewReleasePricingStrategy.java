package rentals.pricingStrategies;

public class NewReleasePricingStrategy implements RentalPricingStrategy {
  @Override
  public double calculatePrice(int numberOfDaysRented) {
    return numberOfDaysRented * 3;
  }
}
