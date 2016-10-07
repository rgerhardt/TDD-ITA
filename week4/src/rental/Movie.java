package rental;

public abstract class Movie {
	
	public static final int CHILDRENS = 2;

	public static final int REGULAR = 0;

	public static final int NEW_RELEASE = 1;

	private String _title;



	public Movie(String title) {
		_title = title;
	}

	public static Movie create(String title, int priceCode) {
		if(priceCode == REGULAR)
			return new Regular(title);
		if(priceCode == NEW_RELEASE) 
			return new NewRealese(title);
		if(priceCode == CHILDRENS) 
			return new Childrens(title);
		throw new RuntimeException("Não existe este tipo de filme");
	}
	


	public String getTitle() {
		return _title;
	};
	
	public int frequentRenterPoints(int daysRented) {
		return 1;
	}
	
	
	public abstract double getAmount(int daysRented);
}