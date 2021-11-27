package transactions;

public class TransactionDetail {
	private String _title;
	private double _rental;

	public TransactionDetail(String movie_name, double rental) {
		_title = movie_name;
		_rental = rental;
	}

	public String get_title() {
		return _title;
	}

	public double get_rental() {
		return _rental;
	}
}
