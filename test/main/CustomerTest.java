package main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import coupons.CouponType;
import products.Movie;
import rentals.RentalFactory;
import rentals.RentalFactory.MovieType;
import transactions.Transaction;

public class CustomerTest {

  @Test
  public void shouldHaveEmptyStatementWithoutRentals() {
    Customer customer = new Customer("Jig", 30);
    String expected = "Rental Record for Jig\n" + "Amount owed is 0.0\n" + "You earned 0 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveRegularRentalStatementWithRegularRentals() {
    Customer customer = new Customer("Ash", 50);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 1, MovieType.REGULAR));
    String expected = "Rental Record for Ash\n" + "\tM1\t2.0\n" + "Amount owed is 2.0\n"
        + "You earned 1 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveRegularRentalStatementWithLongRegularRentals() {
    Customer customer = new Customer("Kartik", 16);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 3, MovieType.REGULAR));
    String expected = "Rental Record for Kartik\n" + "\tM1\t3.5\n" + "Amount owed is 3.5\n"
        + "You earned 1 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveNewReleaseRentalStatementWithNewReleaseRentals() {
    Customer customer = new Customer("Ranveer", 18);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 1, MovieType.NEW_RELEASE));
    String expected = "Rental Record for Ranveer\n" + "\tM1\t3.0\n" + "Amount owed is 3.0\n"
        + "You earned 2 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldAddExtraFrequentRenterPointsForNewReleaseRentalStatementWithForLongNewReleaseRentals() {
    Customer customer = new Customer("Sam", 22);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 3, MovieType.NEW_RELEASE));
    String expected = "Rental Record for Sam\n" + "\tM1\t9.0\n" + "Amount owed is 9.0\n"
        + "You earned 4 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveChildrenRentalStatementWithChildrenRentals() {
    Customer customer = new Customer("Raj", 15);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 1, MovieType.CHILDRENS));
    String expected = "Rental Record for Raj\n" + "\tM1\t1.5\n" + "Amount owed is 1.5\n"
        + "You earned 1 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveChildrenRentalStatementWithLongChildrenRentals() {
    Customer customer = new Customer("Rajesh", 21);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 4, MovieType.CHILDRENS));
    String expected = "Rental Record for Rajesh\n" + "\tM1\t3.0\n" + "Amount owed is 3.0\n"
        + "You earned 1 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldAddMultipleMovieRentalToStatement() {
    Customer customer = new Customer("Rads", 20);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 4, MovieType.REGULAR));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M2"), 5, MovieType.NEW_RELEASE));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M3"), 6, MovieType.CHILDRENS));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M4"), 7, MovieType.NEW_RELEASE));

    String expected = "Rental Record for Rads\n" + "\tM1\t5.0\n" + "\tM2\t15.0\n" + "\tM3\t6.0\n" + "\tM4\t21.0\n"
        + "\tFree Movie for 10 frequent rental points: M4\t-21.0\n"
        + "Amount owed is 26.0\n" + "You earned 2 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnXMLResult() {
    Customer customer = new Customer("Rads", 30);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 4, MovieType.REGULAR));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M2"), 5, MovieType.NEW_RELEASE));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M3"), 6, MovieType.CHILDRENS));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M4"), 7, MovieType.NEW_RELEASE));

    String expected = "<name> Rads </name>\n" + "<movie>\n" + "\t\t<name> M1 </name>\n" + "\t\t<rent> 5.0 </rent>\n"
        + "</movie>\n" + "<movie>\n" + "\t\t<name> M2 </name>\n" + "\t\t<rent> 15.0 </rent>\n" + "</movie>\n"
        + "<movie>\n" + "\t\t<name> M3 </name>\n" + "\t\t<rent> 6.0 </rent>\n" + "</movie>\n" + "<movie>\n"
        + "\t\t<name> M4 </name>\n" + "\t\t<rent> 21.0 </rent>\n" + "</movie>\n"
        + "<movie>\n" + "\t\t<name> Free Movie for 10 frequent rental points: M4 </name>\n" + "\t\t<rent> -21.0 </rent>\n" + "</movie>\n"
        + "<TotalRent> 26.0 </TotalRent>\n"
        + "<FrequentRenterPoints> 2 </FrequentRenterPoints>\n";
    String actual = customer.getXMLStatement();
    assertEquals(expected, actual);
  }

  @Test
  public void shouldHaveDoubleFrequentalPointsForMultipleNewReleasesAndAgeBetween18and22() {
    Customer customer = new Customer("Rajesh", 21);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 4, MovieType.NEW_RELEASE));
    String expected = "Rental Record for Rajesh\n" + "\tM1\t12.0\n" + "Amount owed is 12.0\n"
        + "You earned 4 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M2"), 4, MovieType.NEW_RELEASE));
    String expectedFinal = "Rental Record for Rajesh\n" + "\tM1\t12.0\n" + "\tM2\t12.0\n" + "Amount owed is 24.0\n"
        + "You earned 8 frequent renter points";
    String actualFinal = customer.getTextStatement();
    assertEquals(expectedFinal, actualFinal);
  }

  @Test
  public void shouldReduceTheRentaToHalf() {
    Customer customer = new Customer("Rajesh", 21);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 4, MovieType.NEW_RELEASE));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M2"), 4, MovieType.NEW_RELEASE));
    String expected = "Rental Record for Rajesh\n" + "\tM1\t12.0\n" + "\tM2\t12.0\n" + "Amount owed is 24.0\n"
        + "You earned 8 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
    transaction.addCoupons(new ArrayList<CouponType>(List.of(CouponType.FIFTY_PERCENT_OFF)));
    String expectedFinal = "Rental Record for Rajesh\n" + "\tM1\t12.0\n" + "\tM2\t12.0\n" + "\t50% off\t-12.0\n"
        + "Amount owed is 12.0\n"
        + "You earned 8 frequent renter points";
    String actualFinal = customer.getTextStatement();
    assertEquals(expectedFinal, actualFinal);
  }

  @Test
  public void shouldReduceTheRentaByTenDollarsIfTheTotalPriceIsMoreThanFifty() {
    Customer customer = new Customer("Rajesh", 21);
    Transaction transaction = customer.createTransaction();
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M1"), 24, MovieType.NEW_RELEASE));
    transaction.addProduct(RentalFactory.createMovieRental(new Movie("M2"), 24, MovieType.NEW_RELEASE));
    String expected = "Rental Record for Rajesh\n" + "\tM1\t72.0\n" + "\tM2\t72.0\n" + "Amount owed is 144.0\n"
        + "You earned 8 frequent renter points";
    String actual = customer.getTextStatement();
    assertEquals(expected, actual);
    transaction.addCoupons(new ArrayList<CouponType>(List.of(CouponType.FIFTY_PERCENT_OFF, CouponType.TEN_OFF_ON_FIFTY_OR_MORE_COUPON)));
    String expectedFinal = "Rental Record for Rajesh\n" + "\tM1\t72.0\n" + "\tM2\t72.0\n" + "\t50% off\t-72.0\n"
        + "\t$10 off\t-10.0\n"
        + "Amount owed is 62.0\n"
        + "You earned 8 frequent renter points";
    String actualFinal = customer.getTextStatement();
    assertEquals(expectedFinal, actualFinal);
  }
}
