package transactionFrequentRenterPoints;

public class NewReleaseMovieWithAgeRestrictionFrequentRenterPointsStrategy
    implements TransactionFrequentRenterPointsStrategy {

  private int BONUS_FACTOR = 2;

  @Override
  public int calculateTransactionFrequentRenterPoints(int baseFrequentRenterPoints) {
    return baseFrequentRenterPoints * BONUS_FACTOR;
  }

}
