package com.bank.accountmanagement.Controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.http.HttpHeaders;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.Models.Transaction;
import com.bank.accountmanagement.Services.Transactionstatementservice;

@RestController
public class TransactionStatement {
	
	@Autowired
	private Transactionstatementservice transactionstatement;
	private static final String[] HEADERS = { "Transaction Id", "Current Balance", "Date Time", "Sub Type",
            "Transaction Ref Number", "Transaction Type" };
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);
	
	@GetMapping("api/transaction/get-transaction/{accountNumber}")
	public ResponseEntity<Object> gettransactionlist(@PathVariable long accountNumber){
		List<Transaction> transactionlist= transactionstatement.findTransactionList(accountNumber);
		if(transactionlist.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(transactionlist, HttpStatus.OK);
		
		
	}
	@GetMapping("api/transaction/get-transaction/{accountNumber}/{date}")
	public ResponseEntity<Object> gettransactionlist(@PathVariable long accountNumber,@PathVariable String date){
		List<Transaction> transactionlist= transactionstatement.findTransactionListDate(accountNumber,date);
		if(transactionlist.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(transactionlist, HttpStatus.OK);
		
		
	}
	
	@GetMapping("api/transaction/get-transaction/downloadcsv/{accountNumber}/{date}")
    public ResponseEntity<Object> csv(@PathVariable long accountNumber,@PathVariable String date, HttpServletResponse response)
            throws IOException {
        List<Transaction> listTransactions = transactionstatement.listAll(accountNumber, date);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        final InputStreamResource resource = new InputStreamResource(writeDataToCsv(listTransactions));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users_" + currentDateTime + ".csv")
                .contentType(MediaType.parseMediaType("text/csv")).body(resource);

    }

    private ByteArrayInputStream writeDataToCsv(final List<Transaction> transactions) {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (final Transaction transaction : transactions) {
            	final List<Object> data = Arrays.asList(transaction.getTransactionId(), transaction.getTransactionAmount(),
                        transaction.getDateTime(), transaction.getSubType(), transaction.getTransactionRefNum(),
                        transaction.getTransactionType());

                printer.printRecord(data);
            }

            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }
	

}
