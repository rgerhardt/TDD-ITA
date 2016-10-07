package rental;

public class NewRealese extends Movie {

	public NewRealese(String title) {
		super(title);
	}

	public int frequentRenterPoints(int daysRented) {
		if ( daysRented > 1)
			return 2;
		return 1;
	}
	
	
	public double getAmount(int daysRented) {
		return daysRented * 3;
	}
}
