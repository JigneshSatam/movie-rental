package main;

import pricing.PriceDetail;
import transactions.Transaction;

public class Customer {
  private String _name;
  private int _age;
  private Transaction _transaction = new Transaction(this);;

  public Customer(String name, int age) {
    _name = name;
    _age = age;
  }

  public int get_age() {
    return _age;
  }

  public Transaction createTransaction() {
    return _transaction;
  }

  public String getName() {
    return _name;
  }

  public String getTextStatement() {
    String result = "Transaction Details for " + getName() + "\n";

    for (PriceDetail detail : _transaction.getDetails()) {
      // show figures for the rental
      result += "\t" + detail.get_title() + "\t" + detail.get_price() + "\n";
    }

    // add footer lines
    result += "Amount owed is " + _transaction.calculateTotalRental() + "\n";
    result += "You earned " + _transaction.calculateTotalFrequentRenterPoints() + " frequent renter points";
    return result;
  }

  public String getXMLStatement() {
    String result = "<name> " + getName() + " </name>\n";

    for (PriceDetail detail : _transaction.getDetails()) {
      // show figures for the rental
      result += "<movie>\n\t\t<name> " + detail.get_title() + " </name>\n" + "\t\t<rent> " + detail.get_price()
          + " </rent>\n</movie>\n";
    }

    // add footer lines
    result += "<TotalRent> " + _transaction.calculateTotalRental() + " </TotalRent>\n" + "<FrequentRenterPoints> "
        + _transaction.calculateTotalFrequentRenterPoints() + " </FrequentRenterPoints>\n";
    return result;
  }

  public String getFormattedTextStatement() {
    final String Reset = "\033[0m";
    final String Blue = "\033[1;36m";
    final String Green = "\033[1;32m";
    final String Yellow = "\033[1;33m";
    final String CyanBoldBright = "\033[44m";

    String result = Yellow + "Transaction Details for: " + getName() + Reset + "\n";
    result += String.format("%s%-20s %s%s%s\n", CyanBoldBright, "Item", CyanBoldBright, "Price", Reset);

    for (PriceDetail detail : _transaction.getDetails()) {
      // show figures for the rental
      result += String.format("%s%-20s %s%.2f%s\n", Blue, detail.get_title(), Green, detail.get_price(), Reset);
    }

    // add footer lines
    result += Yellow + "Amount owed is " + _transaction.calculateTotalRental() + Reset + "\n";
    result += Green + "You earned " + _transaction.calculateTotalFrequentRenterPoints() + " frequent renter points" + Reset;
    return result;
  }
}
