package rentals.discountingStrategies;

import transactions.TransactionalProduct;

public class MovieDiscountingStrategy extends DefaultDiscountingStrategy {

  @Override
  public double calculateDiscount(TransactionalProduct product) {
    return product.calculatePrice();
  }
}
