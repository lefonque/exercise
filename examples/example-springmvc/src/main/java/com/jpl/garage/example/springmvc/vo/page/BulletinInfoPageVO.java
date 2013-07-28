package com.jpl.garage.example.springmvc.vo.page;

import com.jpl.garage.example.springmvc.SimpleEnum;
import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

public class BulletinInfoPageVO {

	private BbsDataBean bulletin;
	
	private SimpleEnum mode;

	public BbsDataBean getBulletin() {
		return bulletin;
	}

	public void setBulletin(BbsDataBean bulletin) {
		this.bulletin = bulletin;
	}

	public SimpleEnum getMode() {
		return mode;
	}

	public void setMode(SimpleEnum mode) {
		this.mode = mode;
	}
}
