package transactions;

import java.util.ArrayList;

import coupons.CouponFactory;
import coupons.CouponType;
import discounts.FreeMovie;
import main.Customer;
import pricing.PriceCaclulator;
import pricing.PriceDetail;
import products.movies.Movie;
import products.movies.MovieType;
import transactions.frequentRenterPointsStrategies.DefaultTransactionFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.MultipleMovieTypeFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.TransactionFrequentRenterPointsStrategy;

public class Transaction {
  private Customer _owner;
  private ArrayList<TransactionalProduct> _products = new ArrayList<TransactionalProduct>();
  private TransactionFrequentRenterPointsStrategy _transactionFrequentRenterPointsStrategy;
  private ArrayList<CouponType> _couponTypes = new ArrayList<CouponType>();
  private PriceCaclulator _calculator;

  private boolean reinitialiseCalculator = true;
  private boolean freeMovie = false;
  private int MINIMUM_FREQUENT_RENTER_POINTS_FOR_FREE_MOVIE = 10;
  private int MINIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS = 18;
  private int MAXIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS = 22;

  public Transaction(Customer owner) {
    _owner = owner;
  }

  public void addProduct(TransactionalProduct products) {
    _products.add(products);
    reinitialiseCalculator = true;
  }

  public void addCoupons(ArrayList<CouponType> coupons) {
    _couponTypes.addAll(coupons);
    reinitialiseCalculator = true;
  }

  public ArrayList<PriceDetail> getDetails() {
    return getCalculator().details();
  }

  public double calculateTotalRental() {
    return getCalculator().evaluateCost();
  }

  public int calculateTotalFrequentRenterPoints() {
    setTransactionFrequentRenterPointsStrategy();
    int totalFrequentRenterPoints = _transactionFrequentRenterPointsStrategy
      .calculateTransactionFrequentRenterPoints(calculateBaseFrequentRenterPoints());
    if (freeMovie) {
      totalFrequentRenterPoints -= MINIMUM_FREQUENT_RENTER_POINTS_FOR_FREE_MOVIE;
    }
    return totalFrequentRenterPoints;
  }

  private int calculateBaseFrequentRenterPoints() {
    int baseFrequentRenterPoints = 0;
    for (TransactionalProduct item : _products) {
      baseFrequentRenterPoints += item.calculateFrequentRenterPoints();
    }
    return baseFrequentRenterPoints;
  }

  private void setTransactionFrequentRenterPointsStrategy() {
    if (moreThanTwoMoviesTypes()) {
      _transactionFrequentRenterPointsStrategy = new MultipleMovieTypeFrequentRenterPointsStrategy();
      return;
    }

    if (newReleasePresent() &&
      _owner.get_age() >= MINIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS &&
      _owner.get_age() <= MAXIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS) {
      _transactionFrequentRenterPointsStrategy = new NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy();
      return;
    }

    _transactionFrequentRenterPointsStrategy = new DefaultTransactionFrequentRenterPointsStrategy();
  }

  private boolean moreThanTwoMoviesTypes() {
    TransactionalType firstType = null;
    for (TransactionalProduct product : _products) {
      if (product.getItem() instanceof Movie) {
        if (firstType == null) {
          firstType = product.getType();
          continue;
        }

        if (firstType != product.getType()) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean newReleasePresent() {
    for (TransactionalProduct product : _products) {
      if (product.getItem() instanceof Movie && product.getType() == MovieType.NEW_RELEASE) {
        return true;
      }
    }
    return false;
  }

  private PriceCaclulator getCalculator() {
    if (reinitialiseCalculator) {
      _calculator = new TransactionGroup(_products);
      if (calculateTotalFrequentRenterPoints() > MINIMUM_FREQUENT_RENTER_POINTS_FOR_FREE_MOVIE) {
        _calculator = new FreeMovie(_products, _calculator);
        freeMovie = true;
      }
      for (CouponType couponType : _couponTypes) {
        new CouponFactory();
        _calculator = CouponFactory.getCoupon(couponType, _calculator);
      }
      reinitialiseCalculator = false;
    }
    return _calculator;
  }
}
