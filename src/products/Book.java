package products;

import rentals.Rentable;

public class Book extends Product implements Rentable {
  public Book(String title) {
    super(title);
  }
}
