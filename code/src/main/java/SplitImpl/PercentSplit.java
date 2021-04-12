package SplitImpl;

import Modal.Split;
import Modal.User;

public class PercentSplit extends Split {

	private double percent ;
	public PercentSplit(double amount, User user) {
		super(amount, user);
		// TODO Auto-generated constructor stub
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	

}
