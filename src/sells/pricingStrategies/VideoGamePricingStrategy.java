package sells.pricingStrategies;

public class VideoGamePricingStrategy implements SellPricingStrategy {

  double PRICE_PER_GAME = 30.0;

  @Override
  public double calculatePrice() {
    return PRICE_PER_GAME;
  }

}
