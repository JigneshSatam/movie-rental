package sells;

import products.Candy;
import products.Movie;
import products.MusicCD;
import products.PS4;
import products.VideoGame;
import products.Xbox;
import sells.Sell.MovieType;
import sells.discountingStrategies.DefaultDiscountingStrategy;
import sells.frequentRenterPointsStrategies.DefaultFrequentRenterPointsStrategy;
import sells.pricingStrategies.CandyPricingStrategy;
import sells.pricingStrategies.MoviePricingStrategy;
import sells.pricingStrategies.VideoGamePricingStrategy;
import transactions.TransactionalProduct;

public class SellFactory {
  public static Sell createMovieSell(Movie movie, MovieType type) {
    return new Sell(
      movie, type, new MoviePricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy()
    );
  }

  public static Sell createCandySell(Candy candyBar) {
    return new Sell(
      candyBar, TransactionalProduct.Types.NULL_TYPE,
      new CandyPricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy()
    );
  }

  public static Sell createVideoGameSell(VideoGame game) {
    return new Sell(game, TransactionalProduct.Types.NULL_TYPE,
      new VideoGamePricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy());
  }

  public static Sell createXboxSell(Xbox xbox) {
    return new Sell(
      xbox, TransactionalProduct.Types.NULL_TYPE,
      new VideoGamePricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy());
  }

  public static Sell createPS4Sell(PS4 ps4) {
    return new Sell(
      ps4, TransactionalProduct.Types.NULL_TYPE,
      new VideoGamePricingStrategy(),
      new DefaultDiscountingStrategy(),
      new DefaultFrequentRenterPointsStrategy());
  }

  public static Sell createMusicCDSell(MusicCD cd) {
    return new Sell(
      cd, TransactionalProduct.Types.NULL_TYPE,
      new MoviePricingStrategy(),
      new DefaultDiscountingStrategy(), new DefaultFrequentRenterPointsStrategy());
  }
}
