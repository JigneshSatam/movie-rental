package main;

import java.util.ArrayList;
import java.util.List;

import coupons.CouponType;
import products.candybar.CandyBar;
import products.movies.Movie;
import products.movies.MovieType;
import rentals.RentableType;
import rentals.Rental;
import sales.Sale;
import sales.SellableType;
import transactions.TransactionalType;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer("Sam", 21);
    customer.addRental(new Rental(new Movie("Movie-1"), 10, MovieType.REGULAR));
    customer.addRental(new Rental(new Movie("Movie-2"), 30, MovieType.NEW_RELEASE));
    customer.addRental(new Rental(new Movie("Movie-3"), 20, MovieType.NEW_RELEASE));
    customer.addRental(new Rental(new Movie("Movie-4"), 30, MovieType.CHILDRENS));
    customer.addRental(new Rental(new Movie("Movie-5"), 10, MovieType.CHILDRENS));
    customer.addRental(new Sale(new Movie("CandyBar-1"), TransactionalType.Types.NULL_TYPE));
    customer.addCoupons(
      new ArrayList<CouponType>(
        List.of(
          CouponType.FIFTY_PERCENT_OFF,
          CouponType.TEN_OFF_ON_FIFTY_OR_MORE_COUPON
        )
      )
    );

    System.out.println();
    System.out.println("====Text Output====");
    System.out.println(customer.getTextStatement());

    // System.out.println();
    // System.out.println("====XML Output====");
    // System.out.println(customer.getXMLStatement());

  }

}
