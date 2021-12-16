package transactions;

import java.util.ArrayList;

import coupons.CouponFactory;
import coupons.CouponType;
import discounts.FreeMovieDiscount;
import main.Customer;
import pricing.PriceCaclulator;
import pricing.PriceDetail;
import rentals.Rental;
import sells.Sell;
import transactions.frequentRenterPointsStrategies.DefaultTransactionFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.MultipleMovieTypeFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy;
import transactions.frequentRenterPointsStrategies.TransactionFrequentRenterPointsStrategy;

public class Transaction {
  private int MINIMUM_FREQUENT_RENTER_POINTS_FOR_FREE_MOVIE = 10;
  private int MINIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS = 18;
  private int MAXIMUM_AGE_TO_AVAIL_DOUBLE_FREQUENT_RENTER_POINTS = 22;

  private boolean _reinitialiseCalculator = true;
  private boolean _freeMovie = false;

  private Customer _owner;
  private ArrayList<TransactionalProduct> _products =
    new ArrayList<TransactionalProduct>();
  private TransactionFrequentRenterPointsStrategy
    _transactionFrequentRenterPointsStrategy;
  private ArrayList<CouponType> _couponTypes =
    new ArrayList<CouponType>();
  private PriceCaclulator _calculator;

  public Transaction(Customer owner) {
    _owner = owner;
  }

  public void addProduct(TransactionalProduct products) {
    _products.add(products);
    _reinitialiseCalculator = true;
  }

  public void addCoupons(ArrayList<CouponType> coupons) {
    _couponTypes.addAll(coupons);
    _reinitialiseCalculator = true;
  }

  public ArrayList<PriceDetail> getDetails() {
    return getCalculator().details();
  }

  public double calculateTotalRental() {
    return getCalculator().evaluateCost();
  }

  private PriceCaclulator getCalculator() {
    if (_reinitialiseCalculator) {
      _calculator = new TransactionGroup(_products);

      if (calculateTotalFrequentRenterPoints() > MINIMUM_FREQUENT_RENTER_POINTS_FOR_FREE_MOVIE) {
        _calculator = new FreeMovieDiscount(_products, _calculator);
        _freeMovie = true;
      }

      for (CouponType couponType : _couponTypes) {
        new CouponFactory();
        _calculator = CouponFactory.getCoupon(couponType, _calculator);
      }

      _reinitialiseCalculator = false;
    }
    return _calculator;
  }

  public int calculateTotalFrequentRenterPoints() {
    setTransactionFrequentRenterPointsStrategy();

    int totalFrequentRenterPoints =
      _transactionFrequentRenterPointsStrategy
      .calculateTransactionFrequentRenterPoints(calculateBaseFrequentRenterPoints());

    if (_freeMovie) {
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
      if (firstType == null) {
        firstType = product.getMovieType();
        continue;
      }

      if (firstType != product.getMovieType()) {
        return true;
      }
    }
    return false;
  }

  private boolean newReleasePresent() {
    for (TransactionalProduct product : _products) {
      if (
        product.getMovieType() == Rental.MovieType.NEW_RELEASE ||
        product.getMovieType() == Sell.MovieType.NEW_RELEASE
      ) {
        return true;
      }
    }
    return false;
  }
}
