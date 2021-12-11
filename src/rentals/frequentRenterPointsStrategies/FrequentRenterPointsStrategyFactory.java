package rentals.frequentRenterPointsStrategies;

import products.movies.Movie;
import products.movies.MovieType;
import rentals.Rentable;
import rentals.RentableType;

public class FrequentRenterPointsStrategyFactory {
  public static DefaultFrequentRenterPointsStrategy create(Rentable product, RentableType type) {
    if (product instanceof Movie && type == MovieType.NEW_RELEASE) {
      return new NewReleaseFrequentRenterPointsStrategy();
    }
    return new DefaultFrequentRenterPointsStrategy();
  }
}
