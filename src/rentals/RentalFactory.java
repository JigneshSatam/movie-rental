package rentals;

import products.Book;
import products.Movie;
import products.MusicCD;
import rentals.Rental.MovieType;
import rentals.discountingStrategies.DefaultDiscountingStrategy;
import rentals.discountingStrategies.MovieDiscountingStrategy;
import rentals.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import rentals.frequentRenterPointsStrategies.NewReleaseFrequentRenterPointsStrategy;
import rentals.pricingStrategies.BookPricingStrategy;
import rentals.pricingStrategies.ChildrenPricingStrategy;
import rentals.pricingStrategies.MusicCDPricingStrategy;
import rentals.pricingStrategies.NewReleasePricingStrategy;
import rentals.pricingStrategies.RegularPricingStrategy;
import transactions.TransactionalProduct;

public class RentalFactory {
  public static Rental createMovieRental(Movie movie, int daysRented, MovieType type) {
    switch (type) {
    case NEW_RELEASE:
      return new Rental(movie, daysRented, type,
        new NewReleasePricingStrategy(),
        new NewReleaseFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy());
    case REGULAR:
      return new Rental(movie, daysRented, type,
        new RegularPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy());
    case CHILDRENS:
    default:
      return new Rental(movie, daysRented, type,
        new ChildrenPricingStrategy(),
        new DefaultFrequentRenterPointsStrategy(),
        new MovieDiscountingStrategy());
    }
  }

  public static Rental createBookRental(Book book, int daysRented) {
    return new Rental(
      book, daysRented, TransactionalProduct.Types.NULL_TYPE,
      new BookPricingStrategy(),
      new DefaultFrequentRenterPointsStrategy(),
      new DefaultDiscountingStrategy()
    );
  }

  public static Rental createMusicCDRental(MusicCD cd, int daysRented) {
    return new Rental(
      cd, daysRented, TransactionalProduct.Types.NULL_TYPE,
      new MusicCDPricingStrategy(),
      new DefaultFrequentRenterPointsStrategy(),
      new DefaultDiscountingStrategy());
  }
}
