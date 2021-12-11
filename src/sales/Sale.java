package sales;

import sales.pricingStrategies.DefaultPricingStrategy;
import sales.pricingStrategies.SalePricingStrategy;
import transactions.TransactionalProduct;

public class Sale extends TransactionalProduct {
  private SalePricingStrategy _pricingStrategy;

  public Sale(Sellable item, SellableType type) {
    super(item, type);
    setPricingStrategy(item);
  }

  private void setPricingStrategy(Sellable item) {
    _pricingStrategy = new DefaultPricingStrategy();
  }

  @Override
  public double calculatePrice() {
    return _pricingStrategy.calculatePrice();
  }
}
