package products;

import rentals.Rentable;
import sells.Sellable;

public class MusicCD extends Product implements Rentable, Sellable {
  public MusicCD(String title) {
    super(title);
  }
}
