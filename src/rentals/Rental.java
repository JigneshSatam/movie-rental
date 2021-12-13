package rentals;

import rentals.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import rentals.pricingStrategies.RentalPricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class Rental extends TransactionalProduct {
  private int _daysRented;
  private RentalPricingStrategy _pricingStrategy;
  private DefaultFrequentRenterPointsStrategy _frequentRenterPointsStrategy;

  Rental(
    Rentable product, int daysRented,
    TransactionalType type, RentalPricingStrategy pricingStrategy,
    DefaultFrequentRenterPointsStrategy frequentRenterPointsStrategy
  ) {
    super(product, type);
    _daysRented = daysRented;
    _pricingStrategy = pricingStrategy;
    _frequentRenterPointsStrategy = frequentRenterPointsStrategy;
  }

  public int getDaysRented() {
    return _daysRented;
  }

  @Override
  public double calculatePrice() {
    return _pricingStrategy.calculatePrice(getDaysRented());
  }

  public int calculateFrequentRenterPoints() {
    return _frequentRenterPointsStrategy.calculateFrequentRenterPoints(this);
  }
}
