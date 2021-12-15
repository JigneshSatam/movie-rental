package products;

import rentals.Rentable;
import sells.Sellable;

public class Movie extends Product implements Rentable, Sellable {
  public Movie(String title) {
    super(title);
  }
}
