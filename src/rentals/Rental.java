package rentals;

import rentals.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import rentals.frequentRenterPointsStrategies.FrequentRenterPointsStrategyFactory;
import rentals.pricingStrategies.RentalPricingStrategy;
import rentals.pricingStrategies.RentalPricingStrategyFactory;
import transactions.TransactionalProduct;

public class Rental extends TransactionalProduct {
  private int _daysRented;
  private RentalPricingStrategy _pricingStrategy;
  private DefaultFrequentRenterPointsStrategy _frequentRenterPointsStrategy;

  public Rental(Rentable product, int daysRented, RentableType type) {
    super(product, type);
    _daysRented = daysRented;
    _pricingStrategy = RentalPricingStrategyFactory.create(product, type);
    _frequentRenterPointsStrategy = FrequentRenterPointsStrategyFactory.create(product, type);
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
