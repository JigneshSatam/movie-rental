package transactions;

import rentals.RentalFactory;
import sales.SellFactory;

public abstract class TransactionalProduct  {
  private Transactional _item;
  private TransactionalType _type;

  public abstract double calculatePrice();
  public abstract double calculateDiscount();
  public abstract int calculateFrequentRenterPoints();


  public enum Types implements TransactionalType {
    NULL_TYPE
  }

  public TransactionalProduct(Transactional item, TransactionalType type) {
    _item = item;
    _type = type;
  }

  public Transactional getItem() {
    return _item;
  }

  public String getItemTitle() {
    return getItem().getTitle();
  }

  public TransactionalType getMovieType() {
    for (RentalFactory.MovieType type : RentalFactory.MovieType.values()) {
      if (type == _type) {
        return type;
      }
    }
    for (SellFactory.MovieType type : SellFactory.MovieType.values()) {
      if (type == _type) {
        return type;
      }
    }
    return null;
  }
}
