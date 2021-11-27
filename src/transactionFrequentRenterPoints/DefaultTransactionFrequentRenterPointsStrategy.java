package transactionFrequentRenterPoints;

public class DefaultTransactionFrequentRenterPointsStrategy implements TransactionFrequentRenterPointsStrategy {

  @Override
  public int calculateTransactionFrequentRenterPoints(int baseFrequentRenterPoints) {
    return baseFrequentRenterPoints;
  }

}
