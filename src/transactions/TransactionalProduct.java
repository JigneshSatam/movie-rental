package transactions;

public abstract class TransactionalProduct  {
  private Transactional _item;
  private TransactionalType _type;

  public abstract double calculatePrice();


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

  public int calculateFrequentRenterPoints() {
    return 0;
  }

  public TransactionalType getType() {
    return _type;
  }
}
