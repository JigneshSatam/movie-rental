package main;

import movies.Movie;
import movies.MovieType;
import transactions.Rental;

public class Main {

	public static void main(String[] args) {
		Customer customer = new Customer("Sam", 22);
        customer.addRental(new Rental(new Movie("Movie1"), 1, MovieType.REGULAR));
        customer.addRental(new Rental(new Movie("Movie2"), 3, MovieType.NEW_RELEASE));
        customer.addRental(new Rental(new Movie("Movie3"), 1, MovieType.CHILDRENS));
        
        System.out.println("====Text Output====");
        System.out.println(customer.getTextStatement());
        
        System.out.println();
        System.out.println("====XML Output====");
        System.out.println(customer.getXMLStatement());

	}

}
