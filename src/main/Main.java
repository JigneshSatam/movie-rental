package main;

import java.util.ArrayList;
import java.util.List;

import coupons.CouponType;
import rentals.RentalFactory;
import rentals.RentalFactory.MovieType;
import sales.SellFactory;

public class Main {

  public static void main(String[] args) {
    Customer customer = new Customer("Sam", 21);

    customer.addProduct(
      RentalFactory.createMovie("Rent-Movie-1", 10, MovieType.REGULAR)
    );

    customer.addProduct(
      RentalFactory.createMovie("Rent-Movie-2", 30, MovieType.NEW_RELEASE)
    );

    customer.addProduct(
      RentalFactory.createMovie("Rent-Movie-3", 20, MovieType.CHILDRENS)
    );

    customer.addProduct(
      SellFactory.createMovie("Purchase-Movie-6", SellFactory.MovieType.NEW_RELEASE)
    );

    customer.addProduct(
      SellFactory.createCandyBar("CandyBar-1")
    );

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
