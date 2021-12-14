package rentals;

import products.Movie;
import rentals.discountingStrategies.MovieDiscountingStrategy;
import rentals.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import rentals.frequentRenterPointsStrategies.NewReleaseFrequentRenterPointsStrategy;
import rentals.pricingStrategies.ChildrenPricingStrategy;
import rentals.pricingStrategies.NewReleasePricingStrategy;
import rentals.pricingStrategies.RegularPricingStrategy;
import transactions.TransactionalType;

public class RentalFactory {
  public enum MovieType implements TransactionalType {
    NEW_RELEASE, REGULAR, CHILDRENS;
  }

  public static Rental createMovieRental(Movie movie, int daysRented, MovieType type) {
    switch (type) {
    case NEW_RELEASE:
      return new Rental(
        movie, daysRented, type,
        new NewReleasePricingStrategy(),
        new NewReleaseFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy()
      );
    case REGULAR:
      return new Rental(
        movie, daysRented, type,
        new RegularPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy()
      );
    case CHILDRENS:
    default:
      return new Rental(
        movie, daysRented, type,
        new ChildrenPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy()
      );
    }
  }
}
