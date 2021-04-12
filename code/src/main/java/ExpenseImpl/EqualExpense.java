package ExpenseImpl;

import java.util.Date;
import java.util.List;

import Modal.Expense;
import Modal.Split;
import Modal.User;
import SplitImpl.EqualSplit;

public class EqualExpense extends Expense {

	public EqualExpense(Long id, Double amount, User paidBy, Long groupId, Date createdDate, List<Split> splits) {
		super(id, amount, paidBy, groupId, createdDate, splits);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean validate()
	{
		for (Split splits : super.getSplits())
		{
			if( !(splits instanceof EqualSplit))
			return false;
		}
		return true;
	}
}
