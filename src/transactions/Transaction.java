package transactions;

import java.util.ArrayList;

import coupon.CouponFactory;
import coupon.CouponType;
import main.Customer;
import movies.MovieType;
import pricing.PriceCaclulator;
import rentals.Rental;
import rentals.RentalGroup;
import transactionFrequentRenterPoints.DefaultTransactionFrequentRenterPointsStrategy;
import transactionFrequentRenterPoints.MultipleMovieTypeFrequentRenterPointsStrategy;
import transactionFrequentRenterPoints.NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy;
import transactionFrequentRenterPoints.TransactionFrequentRenterPointsStrategy;

public class Transaction {
  private ArrayList<Rental> _rentals = new ArrayList<Rental>();
  private ArrayList<CouponType> _couponTypes = new ArrayList<CouponType>();
  private Customer _owner;
  private TransactionFrequentRenterPointsStrategy _transactionFrequentRenterPointsStrategy;

  public Transaction(Customer owner) {
    _owner = owner;
  }

  public void addRental(Rental arg) {
    _rentals.add(arg);
  }

  public void addCoupons(ArrayList<CouponType> coupons) {
    _couponTypes.addAll(coupons);
  }

  public ArrayList<TransactionDetail> getDetails() {
    ArrayList<TransactionDetail> details = new ArrayList<TransactionDetail>();
    for (Rental rental : _rentals) {
      details.add(new TransactionDetail(rental.getMovieTitle(), rental.calculateRental()));
    }
    return details;
  }

  public double calculateTotalRental() {
    PriceCaclulator caclulator = new RentalGroup(_rentals);
    for (CouponType couponType : _couponTypes) {
      caclulator = new CouponFactory().getCoupon(couponType, caclulator);
    }
    return caclulator.evaluateCost();
  }

  public int calculateTotalFrequentRenterPoints() {
    setTransactionFrequentRenterPointsStrategy();
    return _transactionFrequentRenterPointsStrategy
        .calculateTransactionFrequentRenterPoints(calculateBaseFrequentRenterPoints());
  }

  int calculateBaseFrequentRenterPoints() {
    int baseFrequentRenterPoints = 0;
    for (Rental rental : _rentals) {
      baseFrequentRenterPoints += rental.calculateFrequentRenterPoints();
    }
    return baseFrequentRenterPoints;
  }

  private void setTransactionFrequentRenterPointsStrategy() {
    if (moreThanTwoMoviesTypes()) {
      _transactionFrequentRenterPointsStrategy = new MultipleMovieTypeFrequentRenterPointsStrategy();
      return;
    }

    if (newReleasePresent() && _owner.get_age() >= 18 && _owner.get_age() <= 22) {
      _transactionFrequentRenterPointsStrategy = new NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy();
      return;
    }

    _transactionFrequentRenterPointsStrategy = new DefaultTransactionFrequentRenterPointsStrategy();
  }

  private boolean moreThanTwoMoviesTypes() {
    MovieType firstType = null;
    for (Rental rental : _rentals) {
      if (firstType == null) {
        firstType = rental.get_movieType();
        continue;
      }

      if (firstType != rental.get_movieType()) {
        return true;
      }
    }
    return false;
  }

  private boolean newReleasePresent() {
    for (Rental rental : _rentals) {
      if (rental.get_movieType() == MovieType.NEW_RELEASE) {
        return true;
      }
    }
    return false;
  }
}
