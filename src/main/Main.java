package main;

import java.util.ArrayList;
import java.util.List;

import coupons.CouponType;
import movies.Movie;
import movies.MovieType;
import rentals.Rental;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer("Sam", 21);
    customer.addRental(new Rental(new Movie("Movie-1"), 10, MovieType.REGULAR));
    customer.addRental(new Rental(new Movie("Movie-2"), 30, MovieType.NEW_RELEASE));
    customer.addRental(new Rental(new Movie("Movie-3"), 20, MovieType.NEW_RELEASE));
    customer.addRental(new Rental(new Movie("Movie-4"), 30, MovieType.CHILDRENS));
    customer.addRental(new Rental(new Movie("Movie-5"), 10, MovieType.CHILDRENS));
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
