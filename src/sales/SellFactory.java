package sales;

import products.CandyBar;
import products.Movie;
import sales.discountingStrategies.DefaultDiscountingStrategy;
import sales.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import sales.pricingStrategies.CandyBarPricingStrategy;
import sales.pricingStrategies.MoviePricingStrategy;
import transactions.TransactionalProduct;
import transactions.TransactionalType;

public class SellFactory {
  public enum MovieType implements TransactionalType {
    NEW_RELEASE, REGULAR, CHILDRENS;
  }

  public static Sell createMovieSell(Movie movie, MovieType type) {
    return new Sell(
      movie, type, new MoviePricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy()
    );
  }

  public static Sell createCandyBarSell(CandyBar candyBar) {
    return new Sell(
      candyBar, TransactionalProduct.Types.NULL_TYPE,
      new CandyBarPricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy()
    );
  }
}
