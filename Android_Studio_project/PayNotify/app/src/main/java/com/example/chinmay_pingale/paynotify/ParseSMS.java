package com.example.chinmay_pingale.paynotify;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseSMS {
	String amt = null,date = null,store = null;
	
	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @param args
	 */
	public void setMessage(String payment) {
		//String payment="Your credit card has been credited with Rs.1000 by FlipKart on 10/02/2015";
		System.out.println("SMS received :"+payment);
		Pattern pattern=Pattern.compile("Rs.(.*?)(?=Rs.|$)");
		
		Matcher matcher = pattern.matcher(payment);
		while (matcher.find()) {
		   //System.out.println(matcher.group(1));
		   String str=matcher.group(1);
		   String[] details = str.split("by");
		   amt=details[0];
		   String[] det = details[1].split("on");
		   store=det[0];
		   date=det[1];
		}
		setAmt(amt.trim());
		setDate(date.trim());
		setStore(store.trim());
		/*System.out.println("Amount:"+amt.trim());
		System.out.println("Store:"+store.trim());
		System.out.println("Date:"+date.trim());*/
	}
	

}
