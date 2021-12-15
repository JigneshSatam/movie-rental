package rentals.pricingStrategies;

public class BookPricingStrategy implements RentalPricingStrategy {

  double PRICE_PER_DAY = 1.0;

  @Override
  public double calculatePrice(int numberOfDaysRented) {
    return numberOfDaysRented * PRICE_PER_DAY;
  }

}
