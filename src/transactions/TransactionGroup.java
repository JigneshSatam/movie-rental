package transactions;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class TransactionGroup implements PriceCaclulator {
  private ArrayList<TransactionalProduct> _products =
    new ArrayList<TransactionalProduct>();

  public TransactionGroup(ArrayList<TransactionalProduct> products) {
    _products = products;
  }

  @Override
  public double evaluateCost() {
    double totalRent = 0;
    for (TransactionalProduct product : _products) {
      totalRent += product.calculatePrice();
    }
    return totalRent;
  }

  @Override
  public ArrayList<PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = new ArrayList<PriceDetail>();
    for (TransactionalProduct product : _products) {
      priceDetails.add(
        new PriceDetail(product.getItemTitle(), product.calculatePrice())
      );
    }
    return priceDetails;
  }
}
