package com.revature.cachemoney.backend.beans.models;

import java.util.Optional;

import com.revature.cachemoney.backend.BackendApplication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransferPayload {

	public int fromId = 0; //accountId
	public int toId = 0;
	//public Account source;
	//public Account target;
	public double amount;

	public TransferPayload(int fromId, int toId, double amount) {
		this.fromId = fromId;
		this.toId = toId;
		//this.source = BackendApplication.acctSvc.getAccountByID(fromId).get();
		//this.target = BackendApplication.acctSvc.getAccountByID(toId).get();
		this.amount = amount;

	}

	/*public TransferPayload(Account from, Account to, double amount) {
		this.source = source;
		this.target = target;
		this.amount = amount;
	}*/

	@Override
	public String toString() {
		String out = "";
		//out += this.source + "\n";
		//out += this.target + "\n";
		out+=BackendApplication.acctSvc.getAccountByID(fromId).get()+"\n";
		out+=BackendApplication.acctSvc.getAccountByID(toId).get()+"\n";
		out += amount + "\n";
		return out;
	}
}
