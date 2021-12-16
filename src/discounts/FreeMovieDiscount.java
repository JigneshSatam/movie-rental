package discounts;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;
import transactions.TransactionalProduct;

public class FreeMovieDiscount implements PriceCaclulator {
  private ArrayList<TransactionalProduct> _products;
  private PriceCaclulator _calculator;

  public FreeMovieDiscount(ArrayList<TransactionalProduct> products, PriceCaclulator calculator) {
    _products = products;
    _calculator = calculator;
  }

  @Override
  public double evaluateCost() {
    return _calculator.evaluateCost() - getMaxRentalMovie().calculateDiscount();
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = _calculator.details();
    TransactionalProduct maxRental = getMaxRentalMovie();
    priceDetails.add(new PriceDetail("Free Movie for 10 frequent rental points: " + maxRental.getItemTitle(),
        maxRental.calculateDiscount() * -1));
    return priceDetails;
  }

  private TransactionalProduct getMaxRentalMovie() {
    TransactionalProduct maxRental = null;
    double maxDiscount = 0.0;
    for (TransactionalProduct product : _products) {
      if (maxDiscount < product.calculateDiscount()) {
        maxRental = product;
        maxDiscount = maxRental.calculateDiscount();
      }
    }
    return maxRental;
  }
}
