package products;

import sales.Sellable;

public class CandyBar extends Product implements Sellable {
  public CandyBar(String title) {
    super(title);
  }
}
