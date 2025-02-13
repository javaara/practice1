package com.mystudy.test;

import java.sql.Date;

public class PaymentVO {
	
	//PAYMENT_UID, customer_id, PAYMENT_CREDIT, TOTAL_PRICE, PAYMENT_DATE, RESERVATION_NUM
	private int payment_uid;
	private String customer_id;
	private String payment_credit;
	private int total_price;
	private java.sql.Date payment_date;
	private int reservation_num;
	//---------------------------------------------------------	
	public PaymentVO(String customer_id) {
		super();
		this.payment_uid = payment_uid;
		this.customer_id = customer_id;
		this.payment_credit = payment_credit;
		this.total_price = total_price;
		this.payment_date = payment_date;
		this.reservation_num = reservation_num;
	}
	
	public PaymentVO(int payment_uid, String customer_id, String payment_credit, int total_price, Date payment_date,
			int reservation_num) {
		super();
		this.payment_uid = payment_uid;
		this.customer_id = customer_id;
		this.payment_credit = payment_credit;
		this.total_price = total_price;
		this.payment_date = payment_date;
		this.reservation_num = reservation_num;
	}
	//---------------------------------------------------------
	public int getPayment_uid() {
		return payment_uid;
	}

	public void setPayment_uid(int payment_uid) {
		this.payment_uid = payment_uid;
	}

	public String getcustomer_id() {
		return customer_id;
	}

	public void setcustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getPayment_credit() {
		return payment_credit;
	}

	public void setPayment_credit(String payment_credit) {
		this.payment_credit = payment_credit;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public java.sql.Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(java.sql.Date payment_date) {
		this.payment_date = payment_date;
	}

	public int getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(int reservation_num) {
		this.reservation_num = reservation_num;
	}
	//---------------------------------------------------------
	@Override
	public String toString() {
		return  payment_uid + ", " + customer_id + ", "+ payment_credit + ", " + total_price + ", " 
				+ payment_date + ", " + reservation_num;
	}
		

}
