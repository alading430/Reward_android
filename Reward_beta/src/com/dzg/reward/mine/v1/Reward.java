package com.dzg.reward.mine.v1;

import java.io.Serializable;

/**
 * @author yufeng.dzg
 * @version:2014-11-4
 */

public class Reward implements Serializable {

	private static final long serialVersionUID = 4450416997333867728L;
	
	private int type;
	
	private String address;
	
	private String date;
	
	private String status;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
