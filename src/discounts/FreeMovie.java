package discounts;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;
import products.movies.Movie;
import rentals.Rental;
import transactions.TransactionalProduct;

public class FreeMovie implements PriceCaclulator {
  private ArrayList<TransactionalProduct> _products;
  private PriceCaclulator _calculator;

  public FreeMovie(ArrayList<TransactionalProduct> products, PriceCaclulator calculator) {
    _products = products;
    _calculator = calculator;
  }

  @Override
  public double evaluateCost() {
    return _calculator.evaluateCost() - getMaxRentalMovie().calculatePrice();
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = _calculator.details();
    TransactionalProduct maxRental = getMaxRentalMovie();
    priceDetails.add(new PriceDetail("Free Movie for 10 frequent rental points: " + maxRental.getItemTitle(),
        maxRental.calculatePrice() * -1));
    return priceDetails;
  }

  private TransactionalProduct getMaxRentalMovie() {
    TransactionalProduct maxRental = null;
    double maxPrice = 0.0;
    for (TransactionalProduct product : _products) {
      if (
        product instanceof Rental &&
        product.getItem() instanceof Movie &&
        maxPrice < product.calculatePrice()) {
        maxRental = product;
        maxPrice = maxRental.calculatePrice();
      }
    }
    return maxRental;
  }
}
