package Modal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Group;

public class BalanceInfo {
	
	
	List<Expense> expenses;
	// userMap: userId to User
	Map<String, User> userMap;
	Map<User, Map<User, Double>> balanceSheet;
	
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public Map<String, User> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}
	public Map<User, Map<User, Double>> getBalanceSheet() {
		return balanceSheet;
	}
	// function to set balance
	public void setBalanceSheet(Map<User, Map<User, Double>> balanceSheet) {
		this.balanceSheet = balanceSheet;
	}
	

}
