package sales;

import products.CandyBar;
import products.Movie;
import sales.pricingStrategies.DefaultPricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class SellFactory {
  public enum MovieType implements TransactionalType {
    NEW_RELEASE, REGULAR, CHILDRENS;
  }

  public static Sell createMovie(String title, MovieType type) {
    return new Sell(
      new Movie(title), type,
      new DefaultPricingStrategy()
    );
  }

  public static Sell createCandyBar(String title) {
    return new Sell(
      new CandyBar(title), TransactionalProduct.Types.NULL_TYPE,
      new DefaultPricingStrategy());
  }
}
