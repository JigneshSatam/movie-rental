package transactions;

import java.util.ArrayList;

import pricing.PriceCaclulator;
import pricing.PriceDetail;

public class TransactionGroup implements PriceCaclulator {
  private ArrayList<TransactionalProduct> _items = new ArrayList<TransactionalProduct>();

  public TransactionGroup(ArrayList<TransactionalProduct> items) {
    _items = items;
  }

  @Override
  public double evaluateCost() {
    double totalRent = 0;
    for (TransactionalProduct item : _items) {
      totalRent += item.calculatePrice();
    }
    return totalRent;
  }

  @Override
  public ArrayList<pricing.PriceDetail> details() {
    ArrayList<PriceDetail> priceDetails = new ArrayList<PriceDetail>();
    for (TransactionalProduct item : _items) {
      priceDetails.add(new pricing.PriceDetail(item.getItemTitle(), item.calculatePrice()));
    }
    return priceDetails;
  }
}
