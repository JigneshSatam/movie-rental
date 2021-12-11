package pricing;

public class PriceDetail {
  private String _title;
  private double _rental;

  public PriceDetail(String movie_name, double rental) {
    _title = movie_name;
    _rental = rental;
  }

  public String get_title() {
    return _title;
  }

  public double get_price() {
    return _rental;
  }
}
