package rentals.pricingStrategies;

public class MusicCDPricingStrategy implements RentalPricingStrategy {

  double PRICE_PER_DAY = 2.0;

  @Override
  public double calculatePrice(int numberOfDaysRented) {
    return numberOfDaysRented * PRICE_PER_DAY;
  }

}
