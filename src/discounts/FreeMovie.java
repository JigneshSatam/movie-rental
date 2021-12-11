package discounts;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;
import rentals.Rental;

public class FreeMovie implements PriceCaclulator {
  private ArrayList<Rental> _rentals;
  private PriceCaclulator _calculator;

  public FreeMovie(ArrayList<Rental> rentals, PriceCaclulator calculator) {
    _rentals = rentals;
    _calculator = calculator;
  }

  @Override
  public double evaluateCost() {
    return _calculator.evaluateCost() - getMaxRental().calculateRental();
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = _calculator.details();
    priceDetails.add(new PriceDetail("Free Movie: " + getMaxRental().getMovieTitle(), getMaxRental().calculateRental() * -1));
    return priceDetails;
  }

  private Rental getMaxRental() {
    Rental maxRental = null;
    double maxPrice = 0.0;
    for (Rental rental : _rentals) {
      if (maxPrice < rental.calculateRental()) {
        maxRental = rental;
        maxPrice = maxRental.calculateRental();
      }
    }
    return maxRental;
  }
}
