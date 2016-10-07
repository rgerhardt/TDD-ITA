package rental;

public class Regular extends Movie {

	public Regular(String title) {
		super(title);
	}
	
	
	public double getAmount(int daysRented) {
		double thisAmount = 2;
		if (daysRented > 2)
			thisAmount += (daysRented - 2) * 1.5;
		return thisAmount;
	}
}
