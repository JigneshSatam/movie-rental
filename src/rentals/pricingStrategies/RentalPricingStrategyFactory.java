package rentals.pricingStrategies;

import products.movies.Movie;
import products.movies.MovieType;
import rentals.Rentable;
import rentals.RentableType;

public class RentalPricingStrategyFactory {

  public static RentalPricingStrategy create(Rentable product, RentableType type) {
    if (product instanceof Movie && type == MovieType.NEW_RELEASE) {
      return new NewReleasePricingStrategy();
    }
    if (product instanceof Movie && type == MovieType.REGULAR) {
      return new RegularPricingStrategy();
    }
    if (product instanceof Movie && type == MovieType.CHILDRENS) {
      return new ChildrenPricingStrategy();
    }
    throw new RuntimeException("Unknow RentalType for class "+ product.getClass().getSimpleName()) ;
  }

}
