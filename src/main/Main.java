package main;

import java.util.ArrayList;
import java.util.List;

import coupons.CouponType;
import products.CandyBar;
import products.Movie;
import rentals.RentalFactory;
import rentals.RentalFactory.MovieType;
import sales.SellFactory;
import transactions.Transaction;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer("Sam", 21);
    Transaction transaction = customer.createTransaction();

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Rent-Movie-1"), 10, MovieType.REGULAR
      )
    );

    transaction.addProduct(
      SellFactory.createMovieSell(
        new Movie("Purchase-Movie-1"), SellFactory.MovieType.NEW_RELEASE
      )
    );

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Rent-Movie-2"), 30, MovieType.NEW_RELEASE
      )
    );

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Rent-Movie-3"), 20, MovieType.CHILDRENS
      )
    );

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Rent-Movie-4"), 20, MovieType.CHILDRENS
      )
    );

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Rent-Movie-5"), 20, MovieType.NEW_RELEASE
      )
    );

    transaction.addProduct(
      SellFactory.createCandyBarSell(new CandyBar("CandyBar-1"))
    );

    transaction.addCoupons(
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
