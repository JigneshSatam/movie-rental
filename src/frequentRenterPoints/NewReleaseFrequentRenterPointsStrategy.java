package frequentRenterPoints;

import rentals.Rental;

public class NewReleaseFrequentRenterPointsStrategy extends DefaultFrequentRenterPointsStrategy {
	@Override
	public int calculateFrequentRenterPoints(Rental rental) {
		if (rental.getDaysRented() > 1) {
			return 2;
		}
		return 1;
	}
}
