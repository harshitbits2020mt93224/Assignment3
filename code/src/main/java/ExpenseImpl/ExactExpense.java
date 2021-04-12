package ExpenseImpl;

import java.util.Date;
import java.util.List;

import Modal.Expense;
import Modal.Split;
import Modal.User;
import SplitImpl.EqualSplit;
import SplitImpl.ExactSplit;

public class ExactExpense extends Expense {

	public ExactExpense(Long id, Double amount, User paidBy, Long groupId, Date createdDate, List<Split> splits) {
		super(id, amount, paidBy, groupId, createdDate, splits);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		int checkAmount = 0 ;
		for (Split splits : super.getSplits())
		{
			if(!(splits instanceof ExactSplit))
				return false;
			checkAmount += ((EqualSplit) splits).getAmount();
		}
		if (checkAmount!= super.getAmount())
			return false;
		return true;
	}

	

}
