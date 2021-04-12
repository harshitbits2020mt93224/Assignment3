package Service;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import Modal.Expense;
import Modal.Split;
import Modal.User;
import enums.Group;

public interface ExpenseManager {
	
	Expense createExpense(Long id,Group type, double amount, User paidBy,long groupId, Date currentDate, List<Split> splits);
	
	void addExpense (Long id, Group type, double amount , String paidBy, long groupId, Date currentDate, List<Split> splits);
	
	void showBalanceForUser (String userId);
	
	void printBalances(String userId, Entry<User, Double> userBalance);
	
	void addUser(User user);
	

}
