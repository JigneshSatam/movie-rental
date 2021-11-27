package rentals;

import java.util.ArrayList;

import pricing.PriceCaclulator;

public class RentalGroup implements PriceCaclulator {
  private ArrayList<Rental> _rentals = new ArrayList<Rental>();

  public RentalGroup(ArrayList<Rental> rentals) {
    _rentals = rentals;
  }

  @Override
  public double evaluateCost() {
    double totalRent = 0;
    for (Rental rental : _rentals) {
      totalRent += rental.calculateRental();
    }
    return totalRent;
  }

}
