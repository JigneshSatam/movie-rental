package products.movies;

import rentals.RentableType;
import sales.SellableType;

public enum MovieType implements RentableType, SellableType {
  NEW_RELEASE, REGULAR, CHILDRENS
}
