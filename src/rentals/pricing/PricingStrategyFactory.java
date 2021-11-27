package rentals.pricing;

import movies.MovieType;

public class PricingStrategyFactory {
  public static PricingStrategy create(MovieType type) {
    switch (type) {
    case NEW_RELEASE:
      return new NewReleasePricingStrategy();
    case REGULAR:
      return new RegularPricingStrategy();
    case CHILDRENS:
    default:
      return new ChildrenPricingStrategy();
    }
  }

}
