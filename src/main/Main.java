package main;

import java.util.ArrayList;
import java.util.List;

import coupons.CouponType;
import products.Book;
import products.Candy;
import products.Movie;
import products.MusicCD;
import products.PS4;
import products.VideoGame;
import products.Xbox;
import rentals.Rental;
import rentals.RentalFactory;
import sells.Sell;
import sells.SellFactory;
import transactions.Transaction;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer("Sam", 21);
    Transaction transaction = customer.createTransaction();

    transaction.addProduct(
      RentalFactory.createMovieRental(
        new Movie("Star Wars"), 5, Rental.MovieType.REGULAR
      )
    );

    transaction.addProduct(
      SellFactory.createMovieSell(
        new Movie("The Matrix"), Sell.MovieType.NEW_RELEASE
      )
    );

    transaction.addProduct(
      RentalFactory.createBookRental(
        new Book("Catch-22"), 30)
    );

    transaction.addProduct(
      RentalFactory.createMusicCDRental(new MusicCD("Freedom"), 20)
    );

    transaction.addProduct(
      SellFactory.createMusicCDSell(new MusicCD("Everyday Life"))
    );

    transaction.addProduct(
      SellFactory.createPS4Sell(new PS4("Call of Duty"))
    );

    transaction.addProduct(
      SellFactory.createVideoGameSell(new VideoGame("Counter Strike"))
    );

    transaction.addProduct(
      SellFactory.createXboxSell(new Xbox("FIFA 21"))
    );

    transaction.addProduct(
      SellFactory.createCandySell(new Candy("Hershey's"))
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
    System.out.println(customer.getFormattedTextStatement());
    System.out.println();

    // System.out.println();
    // System.out.println("====XML Output====");
    // System.out.println(customer.getXMLStatement());

  }
}
