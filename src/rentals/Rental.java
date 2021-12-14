package rentals;

import rentals.discountingStrategies.DefaultDiscountingStrategy;
import rentals.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import rentals.pricingStrategies.RentalPricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class Rental extends TransactionalProduct {
  private int _daysRented;
  private RentalPricingStrategy _pricingStrategy;
  private DefaultFrequentRenterPointsStrategy _frequentRenterPointsStrategy;
  private DefaultDiscountingStrategy _discountingStrategy;

  Rental(
    Rentable product, int daysRented,
    TransactionalType type, RentalPricingStrategy pricingStrategy,
    DefaultFrequentRenterPointsStrategy
      frequentRenterPointsStrategy,
    DefaultDiscountingStrategy discountingStrategy
  ) {
    super(product, type);
    _daysRented = daysRented;
    _pricingStrategy = pricingStrategy;
    _frequentRenterPointsStrategy = frequentRenterPointsStrategy;
    _discountingStrategy = discountingStrategy;
  }

  public int getDaysRented() {
    return _daysRented;
  }

  @Override
  public double calculatePrice() {
    return _pricingStrategy.calculatePrice(getDaysRented());
  }

  @Override
  public double calculateDiscount() {
    return _discountingStrategy.calculateDiscount(this) ;
  }

  @Override
  public int calculateFrequentRenterPoints() {
    return _frequentRenterPointsStrategy.calculateFrequentRenterPoints(this);
  }
}
