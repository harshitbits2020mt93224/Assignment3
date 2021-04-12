package ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import ExpenseImpl.EqualExpense;
import ExpenseImpl.ExactExpense;
import Modal.BalanceInfo;
import Modal.Expense;
import Modal.Split;
import Modal.User;
import Service.ExpenseManager;
import SplitImpl.PercentSplit;
import enums.Group;

public class ExpenseManagerImpl implements ExpenseManager {
	
	List<Expense> expenses;
	// userMap: userId to User
	private Map<String, User> userMap;
	Map<User, Map<User, Double>> balanceSheet;
	
	private static final ExpenseManager expenseService = new ExpenseManagerImpl();

	public Expense createExpense(Long id,Group type, double amount, User paidBy, long groupId, Date currentDate, List<Split> splits) {
		// TODO Auto-generated method stub
		switch(type) {
		case EQUAL:
			int totalSplits = splits.size();
			double splitAmount = (Math.round(amount*100/totalSplits))/100;
			for (Split split: splits)
			{
				split.setAmount(splitAmount);
			}
			return new EqualExpense(id,amount, paidBy, groupId,new Date(), splits);
		case EXACT:
			return new ExactExpense(id,amount, paidBy, groupId,new Date(), splits);
			
		case PERCENT:
			for (Split split:splits){
				PercentSplit percentSplit = (PercentSplit) split;
				split.setAmount(amount*percentSplit.getPercent()/100);
			}
			default :
				System.out.println("Invalid split type");
				return null;
		}
		
	}

	public void addExpense(Long id, Group type, double amount, String userId, long groupId, Date currentDate,
			List<Split> splits) {
		//BalanceInfo info = new BalanceInfo();
	//	Map<String,User> tempUserMap = new HashMap<String,User>();
		//Map<User, Map<User, Double>> balanceSheet = new HashMap<User, Map<User, Double>>();
	//	tempUserMap.put(paidBy.getId(), paidBy);
	//	balanceSheet.put(paidBy, new HashMap<User,Double>());
	//.setUserMap(tempUserMap);
	//	info.setBalanceSheet(balanceSheet);
		List<Expense> expenses = new ArrayList<Expense>();
		User paidBy = userMap.get(userId);
		// TODO Auto-generated method stub
		Expense newExpense = expenseService.createExpense(id, type, amount, paidBy, groupId, currentDate, splits);
		expenses.add(newExpense);
		
		for (Split split : newExpense.getSplits()) {
			User paidTo = split.getUser();
			//Map<User, Double> balances = info.getBalanceSheet().get(paidBy);
			Map<User, Double> balances = balanceSheet.get(paidBy);
			if(!balances.containsKey(paidTo))
				balances.put(paidTo, 0.0);
			
			balances.put(paidTo, balances.get(paidTo)+split.getAmount());
			//balances = info.getBalanceSheet().get(paidTo);
			balances = balanceSheet.get(paidTo);
			
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
		
		
	}

	public void showBalanceForUser(String userId) {
		// TODO Auto-generated method stub
		User thisUser = getUserMap().get(userId);
		if(thisUser == null)
		{
			System.out.println("User doesn't exist");
			return;
		}
		
		for (Map.Entry<User, Double> userBalance : balanceSheet.get(thisUser).entrySet())
		{

			if (userBalance.getKey()!=thisUser) {
				printBalances(userId, userBalance);
			}
		}
	}

	public void printBalances(String userId, Entry<User, Double> userBalance) {
		// TODO Auto-generated method stub
		
		if (userBalance.getValue()!= 0) {
			if (userBalance.getValue()<0) {
				System.out.println(getUserMap().get(userId).getName()+" owes "+Math.abs(userBalance.getValue())+" to "+userBalance.getKey().getName());
				return;
			}
			System.out.println(userBalance.getKey().getName()+" owes "+Math.abs(userBalance.getValue())+" to "+getUserMap().get(userId).getName());
			return;
		}
		System.out.println("No balances for "+userId);
		
	}
	
	public void addUser(User user) {
		getUserMap().put(user.getId(), user);
		balanceSheet.put(user, new HashMap<User, Double>());
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

}
