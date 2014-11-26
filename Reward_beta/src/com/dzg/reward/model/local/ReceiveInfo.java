package com.dzg.reward.model.local;

import java.io.Serializable;

/**
 * @author yufeng.dzg
 * @version:2014-10-10
 */

public class ReceiveInfo implements Serializable {

	private static final long serialVersionUID = -4286224271681265876L;
	
	private long id;
	private String name;
	private int count;
	private int selledCount;
	private String city;
	private String area;
	private String category;
	private String address;
	private String iconUrl;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSelledCount() {
		return selledCount;
	}
	public void setSelledCount(int selledCount) {
		this.selledCount = selledCount;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
}
