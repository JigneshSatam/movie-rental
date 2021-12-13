package products;

import rentals.Rentable;
import sales.Sellable;

public class Movie extends Product implements Rentable, Sellable {
  public Movie(String title) {
    super(title);
  }
}
