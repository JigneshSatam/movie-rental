package transactions;

import rentals.RentableType;
import sales.SellableType;

public interface TransactionalType {
  public enum Types implements RentableType, SellableType {
    NULL_TYPE
  }
}
