package products;

import transactions.Transactional;

public class Product implements Transactional {
  private String _title;

  public Product(String title) {
    _title = title;
  }

  @Override
  public String getTitle() {
    return _title;
  }
}
