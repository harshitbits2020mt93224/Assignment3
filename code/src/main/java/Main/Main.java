package Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Modal.Expense;
import Modal.Split;
import Modal.User;
import Service.ExpenseManager;
import ServiceImpl.ExpenseManagerImpl;
import SplitImpl.EqualSplit;
import SplitImpl.ExactSplit;
import SplitImpl.PercentSplit;
import enums.Group;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  ExpenseManager expenseService = new ExpenseManagerImpl();
		  ExpenseManagerImpl expenseManager = new ExpenseManagerImpl();
		  expenseService.addUser(new User("pradipta", "Pradipta Sarma", "pradiptasarma@outlook.com", "8133910729"));
		  expenseService.addUser(new User("nasir", "Nasir Ahmed", "nasirkvbarpeta@gmail.com", "0000999909"));
		  expenseService.addUser(new User("hemant", "Hemant Saikia", "hmnt.ska23@gmail.com", "7123123123"));
	
		  User u1 = new User("pradipta", "Pradipta Sarma", "pradiptasarma@outlook.com", "8133910729");
		  Scanner scanner = new Scanner(System.in);
			while (true){
				System.out.println("Select mode: Create Expense [1], Show Balance [2]");
				String input = scanner.nextLine();
				if(issNull(input))
				{
				switch (input){
					case "1":
						//new expense
						createExpense(expenseManager, scanner);
						break;
					case "2":
						viewBalance(expenseManager, scanner);
						break;
				}
			}
				
			}
		}
	
	private static void viewBalance(ExpenseManagerImpl expenseManager, Scanner scanner) {
		System.out.println("Enter userid for whom you want to see balance:");
		String userId = scanner.nextLine();
		//TODO put check
		expenseManager.showBalanceForUser(userId);
	}
	private static void createExpense(ExpenseManagerImpl expenseManager, Scanner scanner) {
		System.out.println("Choose split type:");
		System.out.println("1. Equal");
		System.out.println("2. Exact");
		System.out.println("3. Percent");

		String splitType = scanner.nextLine();

		switch(splitType){
			case "1":
				// case to handle Equal input
				createEqualExpense(expenseManager, scanner);
				break;
			case "2":
				// case to handle Exact input
				createExactExpense(expenseManager, scanner);
				break;
			case "3":				
				// case to handle Percent input
				createPercentExpense(expenseManager, scanner);
				break;
			default:
				System.out.println("Incorrect option");
				return;
		}
	}
	
	private static void createEqualExpense(ExpenseManagerImpl ExpenseManagerImpl, Scanner scanner) {
		System.out.println("Enter no. of Users:");
		int noOfUsers = Integer.parseInt(scanner.nextLine());
		List<Split> splits = new ArrayList<>();
		System.out.println("Who paid?");
		String paidBy = scanner.nextLine();
		System.out.println("What did you spend on?");
		String label = scanner.nextLine();
		System.out.println("Enter amount");
		double amount = Double.parseDouble(scanner.nextLine());
		splits.add(new EqualSplit(amount,ExpenseManagerImpl.getUserMap().get(paidBy)));
		for (int i = 1; i<noOfUsers; i++){
			System.out.println("Participant "+(i+1));
			System.out.println("Enter user id: ");
			String userIdNow = scanner.nextLine();
			//TODO put check if user exists
			splits.add(new EqualSplit(amount,ExpenseManagerImpl.getUserMap().get(userIdNow)));
		}
		
	//	expenseManager.addExpense(Group.EQUAL, amount, paidBy, splits, label);
		ExpenseManagerImpl.addExpense(((long)2),Group.EQUAL, amount, paidBy,((long)2),new Date(), splits);
		ExpenseManagerImpl.showBalanceForUser(paidBy);
	}
	
	private static void createExactExpense(ExpenseManagerImpl expenseManager, Scanner scanner) {
		System.out.println("Enter no. of Users:");
		int noOfUsers = Integer.parseInt(scanner.nextLine());
		List<Split> splits = new ArrayList<>();
		System.out.println("Who paid?");
		String paidBy = scanner.nextLine();
		System.out.println("What did you spend on?");
		String label = scanner.nextLine();
		System.out.println("Enter total amount");
		double amount = Double.parseDouble(scanner.nextLine());

		System.out.println("Enter amount paid by "+paidBy+":");
		double amountIndi = Double.parseDouble(scanner.nextLine());
		double amountCheck = amountIndi;

		splits.add(new ExactSplit(amountIndi,expenseManager.getUserMap().get(paidBy)));
		// loop to scan numbers
		for (int i = 1; i<noOfUsers; i++){
			System.out.println("Participant "+(i+1));
			System.out.println("Enter user id: ");
			String userIdNow = scanner.nextLine();
			System.out.println("Enter amount paid by "+userIdNow+":");
			amountIndi = Double.parseDouble(scanner.nextLine());
			splits.add(new ExactSplit(amountIndi,expenseManager.getUserMap().get(userIdNow)));
		}
		//TODO check total amount equal to sum of all
		expenseManager.addExpense(((long)3),Group.EXACT, amount, paidBy,((long)2),new Date(), splits);
		expenseManager.showBalanceForUser(paidBy);
	}
	
	private static void createPercentExpense(ExpenseManagerImpl expenseManager, Scanner scanner) {
		System.out.println("Enter no. of Users:");
		int noOfUsers = Integer.parseInt(scanner.nextLine());
		List<Split> splits = new ArrayList<>();
		System.out.println("Who paid?");
		String paidBy = scanner.nextLine();
		System.out.println("What did you spend on?");
		String label = scanner.nextLine();
		System.out.println("Enter amount");
		double amount = Double.parseDouble(scanner.nextLine());

		System.out.println("Enter percentage shared by "+paidBy+":");
		double percentIndi = Double.parseDouble(scanner.nextLine());
		double percentCheck = percentIndi;

		//TODO check for total percentage
		splits.add(new PercentSplit(percentIndi, expenseManager.getUserMap().get(paidBy)));

		for (int i = 1; i<noOfUsers; i++){
			System.out.println("Participant "+(i+1));
			System.out.println("Enter user id: ");
			String userIdNow = scanner.nextLine();
			System.out.println("Enter percentage shared by "+userIdNow+":");
			percentIndi = Double.parseDouble(scanner.nextLine());
			splits.add(new PercentSplit(percentIndi,expenseManager.getUserMap().get(userIdNow)));
		}

		expenseManager.addExpense(((long)3),Group.EXACT, amount, paidBy,((long)2),new Date(), splits);
		expenseManager.showBalanceForUser(paidBy);
	}
	
	}
