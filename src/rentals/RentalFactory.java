package rentals;

import products.Movie;
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

  public static Rental createMovie(String title, int daysRented, MovieType type) {
    switch (type) {
    case NEW_RELEASE:
      return new Rental(
        new Movie(title), daysRented, type,
        new NewReleasePricingStrategy(),
        new NewReleaseFrequentRenterPointsStrategy()
      );
    case REGULAR:
      return new Rental(
        new Movie(title), daysRented, type,
        new RegularPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy()
      );
    case CHILDRENS:
    default:
      return new Rental(
        new Movie(title), daysRented, type,
        new ChildrenPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy()
      );
    }
  }
}
