package sales;

import sales.pricingStrategies.SalePricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class Sell extends TransactionalProduct {
  private SalePricingStrategy _pricingStrategy;

  public Sell(
    Sellable product, TransactionalType type,
    SalePricingStrategy pricingStrategy
  ) {
    super(product, type);
    _pricingStrategy = pricingStrategy;
  }

  @Override
  public double calculatePrice() {
    return _pricingStrategy.calculatePrice();
  }
}
