package main;

import transactions.Rental;
import transactions.Transaction;
import transactions.TransactionDetail;

public class Customer {
    private String _name;
    private int _age;
    private Transaction _transaction = new Transaction(this);

    public Customer(String name, int age) {
        _name = name;
        _age = age;
    }

    public int get_age() {
        return _age;
    }

    public void addRental(Rental arg) {
        _transaction.addRental(arg);
    }

    public String getName() {
        return _name;
    }

    public String getTextStatement() {
        String result = "Rental Record for " + getName() + "\n";

        for (TransactionDetail detail : _transaction.getDetails()) {
            // show figures for the rental
            result += "\t" + detail.get_title() + "\t" + detail.get_rental() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + _transaction.calculateTotalRental() + "\n";
        result += "You earned " + _transaction.calculateTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    public String getXMLStatement() {
        String result = "<name> " + getName() + " </name>\n";

        for (TransactionDetail detail : _transaction.getDetails()) {
            // show figures for the rental
            result += "<movie>\n\t\t<name> " + detail.get_title() + " </name>\n" + "\t\t<rent> " + detail.get_rental()
                    + " </rent>\n</movie>\n";
        }

        // add footer lines
        result += "<TotalRent> " + _transaction.calculateTotalRental() + " </TotalRent>\n" + "<FrequentRenterPoints> "
                + _transaction.calculateTotalFrequentRenterPoints() + " </FrequentRenterPoints>\n";
        return result;
    }
}
