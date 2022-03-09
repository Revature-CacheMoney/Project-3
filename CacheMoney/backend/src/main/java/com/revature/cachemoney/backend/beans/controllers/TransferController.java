package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.BackendApplication;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.models.TransferPayload;
import com.revature.cachemoney.backend.beans.services.TransactionService;

/**
 * Handles HTTP requests for account transactions. maybe should roll in with
 * Account or Transaction Service
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

	private final TransactionService transactionService;

	@Autowired
	public TransferController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping()
	public void postTransfer(@RequestBody TransferPayload transfer) {
		System.out.println(transfer);

		// Transaction from = new Transaction(transfer.source, "Transfer",
		// transfer.amount,
		// transfer.source.getBalance() - transfer.amount);
		// Transaction to = new Transaction(transfer.target, "Transfer",
		// transfer.amount,
		// transfer.target.getBalance() + transfer.amount);
		Account src = BackendApplication.acctSvc.getAccountByID(transfer.fromId).get();
		Account tgt = BackendApplication.acctSvc.getAccountByID(transfer.toId).get();
		Transaction from = new Transaction(src, "Transfer", transfer.amount,
				src.getBalance() - transfer.amount);
		Transaction to = new Transaction(tgt, "Transfer", transfer.amount,
				tgt.getBalance() + transfer.amount);

		src.setBalance(src.getBalance()-transfer.amount);
		tgt.setBalance(tgt.getBalance()+transfer.amount);
		
		
		
		
		transactionService.postTransaction(from);
		transactionService.postTransaction(to);
		
		BackendApplication.acctSvc.postAccount(src);
		BackendApplication.acctSvc.postAccount(tgt);
	}

}
