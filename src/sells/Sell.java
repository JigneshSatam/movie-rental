package sells;

import sells.discountingStrategies.DefaultDiscountingStrategy;
import sells.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import sells.pricingStrategies.SellPricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class Sell extends TransactionalProduct {
  private SellPricingStrategy _pricingStrategy;
  private DefaultDiscountingStrategy _discountingStrategy;
  private DefaultFrequentRenterPointsStrategy
    _frequentRenterPointsStrategy;

  public enum MovieType implements TransactionalType {
    NEW_RELEASE, REGULAR, CHILDRENS;
  }

  public Sell(
    Sellable product, TransactionalType type,
    SellPricingStrategy pricingStrategy,
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
