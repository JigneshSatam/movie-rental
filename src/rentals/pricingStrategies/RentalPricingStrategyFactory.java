package rentals.pricingStrategies;

import movies.MovieType;

public class RentalPricingStrategyFactory {

  public static RentalPricingStrategy create(MovieType type) {
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
