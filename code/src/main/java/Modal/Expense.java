package Modal;

import java.util.Date;
import java.util.List;

public abstract class Expense {
	
	private Long id;
	
	private Double amount;
	
	private User paidBy;
	
	private Long groupId;
	
	private Date createdDate ;
	
	 private List<Split> splits;
	
	public Expense(Long id, Double amount, User paidBy, Long groupId, Date createdDate, List<Split> splits) {
		this.id = id;
		this.amount = amount;
		this.paidBy = paidBy;
		this.groupId = groupId;
		this.createdDate = createdDate;
		this.splits = splits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Split> getSplits() {
		return splits;
	}

	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}
	
	public abstract boolean validate();
	
	
	

}
