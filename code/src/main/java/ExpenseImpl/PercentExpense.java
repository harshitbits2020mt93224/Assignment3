package ExpenseImpl;

import java.util.Date;
import java.util.List;

import Modal.Expense;
import Modal.Split;
import Modal.User;
import SplitImpl.PercentSplit;

public class PercentExpense extends Expense {

	public PercentExpense(Long id, Double amount, User paidBy, Long groupId, Date createdDate, List<Split> splits) {
		super(id, amount, paidBy, groupId, createdDate, splits);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		int checkPercent = 0 ;
		double totalPercent = 100;
		for (Split splits:super.getSplits())
		{
			if(!(splits instanceof PercentSplit))
				return false;
			PercentSplit percentSplit = (PercentSplit) splits;
			checkPercent += percentSplit.getPercent();
		}
		if(checkPercent != totalPercent)
			return false;
		return true;
	}

}
