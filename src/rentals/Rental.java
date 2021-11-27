package rentals;

import frequentRenterPoints.DefaultFrequentRenterPointsStrategy;
import frequentRenterPoints.NewReleaseFrequentRenterPointsStrategy;
import movies.Movie;
import movies.MovieType;
import rentals.pricing.PricingStrategy;
import rentals.pricing.PricingStrategyFactory;

public class Rental {
  private Movie _movie;
  private int _daysRented;
  private MovieType _movieType;
  private PricingStrategy _pricingStrategy;
  private DefaultFrequentRenterPointsStrategy _frequentRenterPointsStrategy;

  public Rental(Movie movie, int daysRented, MovieType type) {
    _movie = movie;
    _daysRented = daysRented;
    _movieType = type;
    _pricingStrategy = PricingStrategyFactory.create(type);
    setFrequentRenterPointsStrategy(type);
  }

  void setFrequentRenterPointsStrategy(MovieType type) {
    switch (type) {
    case NEW_RELEASE:
      _frequentRenterPointsStrategy = new NewReleaseFrequentRenterPointsStrategy();
      break;
    default:
      _frequentRenterPointsStrategy = new DefaultFrequentRenterPointsStrategy();
    }
  }

  public int getDaysRented() {
    return _daysRented;
  }

  public Movie getMovie() {
    return _movie;
  }

  public double calculateRental() {
    return _pricingStrategy.calculatePrice(getDaysRented());
  }

  public int calculateFrequentRenterPoints() {
    return _frequentRenterPointsStrategy.calculateFrequentRenterPoints(this);
  }

  public String getMovieTitle() {
    return getMovie().getTitle();
  }

  public MovieType get_movieType() {
    return _movieType;
  }
}
