package sales;

import sales.discountingStrategies.DefaultDiscountingStrategy;
import sales.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import sales.pricingStrategies.SalePricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class Sell extends TransactionalProduct {
  private SalePricingStrategy _pricingStrategy;
  private DefaultDiscountingStrategy _discountingStrategy;
  private DefaultFrequentRenterPointsStrategy
    _frequentRenterPointsStrategy;


  public Sell(
    Sellable product, TransactionalType type,
    SalePricingStrategy pricingStrategy,
    DefaultDiscountingStrategy discountingStrategy,
    DefaultFrequentRenterPointsStrategy frequentRenterPointsStrategy
  ) {
    super(product, type);
    _pricingStrategy = pricingStrategy;
    _discountingStrategy = discountingStrategy;
    _frequentRenterPointsStrategy = frequentRenterPointsStrategy;
  }

  @Override
  public double calculatePrice() {
    return _pricingStrategy.calculatePrice();
  }

  @Override
  public double calculateDiscount() {
    return _discountingStrategy.calculateDiscount();
  }

  @Override
  public int calculateFrequentRenterPoints() {
    return _frequentRenterPointsStrategy.calculateFrequentRenterPoints();
  }
}
