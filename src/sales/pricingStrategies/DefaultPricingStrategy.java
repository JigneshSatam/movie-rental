package sales.pricingStrategies;

public class DefaultPricingStrategy implements SalePricingStrategy {

  @Override
  public double calculatePrice() {
    return 1000;
  }

}
