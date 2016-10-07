package rental;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	
	private String _name;
	private Vector _rentals = new Vector();
	private int _frequentRenterPoints;
	private double _totalAmount;

	public Customer(String name) {
		_name = name;
		_frequentRenterPoints = 0;
		_totalAmount = 0;
	};

	public void addRental(Rental rental) {
		_rentals.addElement(rental);
		_frequentRenterPoints += rental.frequentRenterPoints();
		_totalAmount += rental.getAmount();
	}

	public String getName() {
		return _name;
	}

	public String statement() {

		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();

			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(each.getAmount()) + "\n";

		}
		//add footer lines
		result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints())
				+ " frequent renter points";
		return result;
	}
	
	public double getTotalAmount() {
		return _totalAmount;
	}
	
	
	public int getTotalFrequentRenterPoints() {
		return _frequentRenterPoints;
	}


}