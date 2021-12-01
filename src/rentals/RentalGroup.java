package rentals;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

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

  @Override
  public ArrayList<pricing.PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = new ArrayList<PriceDetail>();
    for (Rental rental : _rentals) {
      priceDetails.add(new pricing.PriceDetail(rental.getMovieTitle(), rental.calculateRental()));
    }
    return priceDetails;
  }
}
