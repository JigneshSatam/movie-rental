package products.candybar;

import products.Product;
import sales.Sellable;

public class CandyBar extends Product implements Sellable {

  public CandyBar(String title) {
    super(title);
  }
}
