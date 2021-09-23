package com.bank.accountmanagement.ServiceImpls;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.bank.accountmanagement.Models.Account;
import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Models.Customer;
import com.bank.accountmanagement.Exception.InsufficientBalanceException;
import com.bank.accountmanagement.Exception.MaxWithdrawalLimitExceededException;
import com.bank.accountmanagement.Repositories.AccountRepo;
import com.bank.accountmanagement.Repositories.TransactionRepo;
import com.bank.accountmanagement.Service.TransactionService;
import java.util.List;


@Component
public class TransactionServiceImpl implements TransactionService {
	
	
	
//******************* BY SHYAM ***********************	
	
	@Override
	public String Deposit(long depositID, double amount) {
	
		Account depositor=accountRepo.getById(depositID);
		
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount(amount);
		depositor.setCurrentBalance(depositor.getCurrentBalance() + amount);
		transaction.setTransactionRefNum("T"+transaction.getTransactionId()+"-"+depositor.getAccountNumber());
		//transaction.setTransactionRefNum("T"+depositor.getAccountNumber()+String.format("%d",transaction.getTransactionId()));
		transaction.setDateTime(LocalDateTime.now());
		transaction.setTransactionType("Credit");
		transaction.setSubType("Deposit");
		

		
		depositor.getTransactions().add(transaction);
		
		//transactionRepo.save(transaction);
		accountRepo.save(depositor);
		GenerateTransactionRef(transaction,depositor);
		//sendEmail(depositor, transaction);	
		return "Transaction successful";
	}
//**********************BY SMRUTHI*********************************
	
	@Autowired
    private JavaMailSender mailSender;
//Email Module
	@Override
    public void sendEmail(Account account, Transaction transaction) {
        SimpleMailMessage message = new SimpleMailMessage();

        Customer customer = account.getCustomer();
        String emailId = customer.getEmail();
        System.out.println(emailId);
        message.setFrom("acc.management.system@gmail.com");
        message.setTo(emailId);
        
        String firstEncryptedAccountNumber = Long.toString(account.getAccountNumber()).substring(0, 3); 
        String lastEncryptedAccountNumber = Long.toString(account.getAccountNumber()).substring(5, 9);
        String encryptedAccountNumber = firstEncryptedAccountNumber +"XXX"+ lastEncryptedAccountNumber;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    String now = dtf.format(transaction.getDateTime());  

	    String subjectStart = "Your account";
	    String subjectEnd;
	    
	    if(transaction.getTransactionType().equals("Credit")) {
	    	subjectEnd = "has been credited.";
	    }else {
	    	subjectEnd = "has been debited.";
	    }
	    
        message.setText("Transaction successful! \nTransaction reference number : " + transaction.getTransactionRefNum() +
        		"\nTransaction Date : " + now +
                "\nTransaction Type : " + transaction.getTransactionType() + 
        		"\nAmount transacted : $" + transaction.getTransactionAmount());
        message.setSubject(subjectStart + " " + encryptedAccountNumber + " " +subjectEnd);

        mailSender.send(message);
        System.out.println("Mail sent...");
    }
//Withdraw Module		
		@Override
		public Long getAmount() { 
			Long amt = (long) 100;
			return amt;
		}
		@Override
		public Long getCurrentBalance(long accountNumber) {
			Account account = accountRepo.getById(accountNumber);
			Long currentBalance = (long) account.getCurrentBalance();
			return currentBalance;
		}
	 
	@Override
	public Long DeductMoney(long accountNumber, Long amountToWithdraw) { 
		

			Account account = accountRepo.getById(accountNumber);
			
			if (! (checkLimit10000(accountNumber, amountToWithdraw)) ) {
				throw new MaxWithdrawalLimitExceededException();
			}
			
			Long currentBalance = getCurrentBalance(accountNumber);
		    Long newBalance = currentBalance - amountToWithdraw;
		    
			if(newBalance < 0 ) { 
				throw new InsufficientBalanceException(); }
			
			account.setCurrentBalance(newBalance);
			Transaction transaction = Transfer(amountToWithdraw, accountNumber);
			accountRepo.save(account);
			
			try {
				GenerateTransactionRef(transaction, account);
				//sendEmail(account, transaction);
			}catch( Exception e ){
				// catch error
				System.out.println("Error Sending Email: " + e.getMessage());
			}
			
		    return getCurrentBalance(accountNumber);
		}

	@Override
	public boolean checkLimit10000(long accountNumber, Long amountToWithdraw) {
			
			Account account = accountRepo.getById(accountNumber);
			List<Transaction> transactionList = account.getTransactions();
			
			int today = LocalDateTime.now().getDayOfMonth();
			
			for(Transaction transaction : transactionList) {
				System.out.println("DATE TIME -> "+transaction.getDateTime().getDayOfMonth());
				System.out.println("LIMIT - " + account.getDailyLimit());
				if(today == transaction.getDateTime().getDayOfMonth()) {
					if(transaction.getTransactionType().equals("Withdrawal"))
					{
						account.setDailyLimit(account.getDailyLimit() + transaction.getTransactionAmount());
					}
				}else {
					account.setDailyLimit(0.0);
				}
			}
			if(account.getDailyLimit() - amountToWithdraw < -10000) {
				return false;
			}
			account.setDailyLimit(account.getDailyLimit() - amountToWithdraw);
			return true;
			}

	Transaction Transfer(double amountToWithdraw, long accountNumber) {
			
	    	Account account = accountRepo.getById(accountNumber);
	    	Transaction transaction = new Transaction();
			transaction.setTransactionAmount(-amountToWithdraw);
		//	transaction.setCurrentBalance(reciever.getCurrentBalance() + amount);
			transaction.setTransactionRefNum("T"+transaction.getDateTime().getDayOfMonth()
					+":"+transaction.getDateTime().getHour()+":"+transaction.getDateTime().getMinute()
					+"-"+account.getAccountNumber());
			transaction.setTransactionType("Debit");
			transaction.setSubType("Withdrawal");
			
			//transactionRepo.save(transaction);
			account.getTransactions().add(transaction);
			//accountRepo.save(account);
			return transaction;
	}
	
//**********************BY ASWATTH*********************************	
	
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	TransactionRepo transactionRepo;
	
	@Override
	public String Transfer(long senderId, double amount, long recieverId) {
		
		Account sender = accountRepo.getById(senderId) ;
		Account reciever = accountRepo.getById(recieverId);
		
		if(sender.getCurrentBalance() < amount)
			return "Insufficient balance";
		if(amount < 0)
			return "Invalid amount";
		
		LocalDateTime timeStamp = LocalDateTime.now();
		
		Transaction senderTransaction = new Transaction();
		sender.setCurrentBalance(sender.getCurrentBalance() - amount);
		senderTransaction.setTransactionAmount(-amount);
		senderTransaction.setTransactionType("Debit");
		senderTransaction.setSubType("Transfer");
		senderTransaction.setDateTime(timeStamp);
		senderTransaction.setTransactionRefNum("T"+senderTransaction.getTransactionId()+"-"+sender.getAccountNumber()+"-"+reciever.getAccountNumber());
		sender.getTransactions().add(senderTransaction);
		accountRepo.save(sender);
		
		senderTransaction = GenerateRef(sender);
		int senderTID = senderTransaction.getTransactionId();
		//sendEmail(sender, senderTransaction);
		//transactionRepo.save(senderTransaction);
	
		Transaction recieverTransaction = new Transaction();
		reciever.setCurrentBalance(reciever.getCurrentBalance() + amount);
		recieverTransaction.setTransactionAmount(amount);
		recieverTransaction.setTransactionType("Credit");
		recieverTransaction.setSubType("Transfer");
		recieverTransaction.setDateTime(timeStamp);
		recieverTransaction.setTransactionRefNum("T"+recieverTransaction.getTransactionId()+"-"+sender.getAccountNumber()+"-"+reciever.getAccountNumber());
		reciever.getTransactions().add(recieverTransaction);
		accountRepo.save(reciever);	

		


		recieverTransaction = GenerateRef(reciever);
		int recieverTID = recieverTransaction.getTransactionId();
		
		recieverTransaction.setTransactionRefNum("T"+senderTID+"-"+recieverTID);
		senderTransaction.setTransactionRefNum("T"+senderTID+"-"+recieverTID);
		
		saveAndNotify(sender, senderTransaction);
		saveAndNotify(reciever, recieverTransaction);
			
		return "Successfull transfer";
		
		
		}
	
	@Override
	public String checkHistory(long accountNum) {
		Account account = accountRepo.getById(accountNum);
		List<Transaction> transactionList = account.getTransactions();
		
		int sizeOfList = transactionList.size();
		
		if(sizeOfList > 5)
		{
			System.out.println(transactionList.get(sizeOfList-1).toString());
			System.out.println(transactionList.get(sizeOfList-2).toString());
			System.out.println(transactionList.get(sizeOfList-3).toString());
			System.out.println(transactionList.get(sizeOfList-4).toString());
			System.out.println(transactionList.get(sizeOfList-5).toString());
			return "Fetched history";
		}
		else {
			for(Transaction transaction : transactionList) {
				System.out.println(transaction.toString());
			}
			return "Fetched history";
		}
		//return "Unable to fetch history";
	}
	void GenerateTransactionRef(Transaction transaction,Account account) {
		Page<Transaction> page = transactionRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "transactionId")));
		transaction = page.getContent().get(0);
		transaction.setTransactionRefNum("T"+transaction.getTransactionId()+":"+account.getAccountNumber());
		//transaction.setTransactionId(transaction.getTransactionId());
		saveAndNotify(account, transaction);
	}
	Transaction GenerateRef(Account account) {
		List<Transaction> transactions = accountRepo.getById(account.getAccountNumber()).getTransactions();
		return transactions.get(transactions.size() - 1);
	}
	void saveAndNotify(Account account,Transaction transaction) {
		accountRepo.save(account);
		sendEmail(account, transaction);
	}


}
