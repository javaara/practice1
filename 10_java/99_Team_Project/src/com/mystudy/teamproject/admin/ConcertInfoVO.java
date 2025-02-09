package com.mystudy.teamproject.admin;

import java.sql.Date;

public class ConcertInfoVO {


	int concert_num;
	String concert_name;
	int hall_num;
	String concert_date;
	
	
	public ConcertInfoVO(int concert_num, String concert_name, int hall_num, String concert_date) {
		super();
		this.concert_num = concert_num;
		this.concert_name = concert_name;
		this.hall_num = hall_num;
		this.concert_date = concert_date;
	}


	public int getConcert_num() {
		return concert_num;
	}


	public void setConcert_num(int concert_num) {
		this.concert_num = concert_num;
	}


	public String getConcert_name() {
		return concert_name;
	}


	public void setConcert_name(String concert_name) {
		this.concert_name = concert_name;
	}


	public int getHall_num() {
		return hall_num;
	}


	public void setHall_num(int hall_num) {
		this.hall_num = hall_num;
	}


	public String getConcert_date() {
		return concert_date;
	}


	public void setConcert_date(String concert_date) {
		this.concert_date = concert_date;
	}


	@Override
	public String toString() {
		return "ConcertInfoVO [concert_num=" + concert_num + ", concert_name=" + concert_name + ", hall_num="
				+ hall_num + ", concert_date=" + concert_date + "]";
	}
	
	

}
