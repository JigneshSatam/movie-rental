package pricing;

import java.util.ArrayList;

public interface PriceCaclulator {
  public double evaluateCost();
  public ArrayList<pricing.PriceDetail> details();
}
