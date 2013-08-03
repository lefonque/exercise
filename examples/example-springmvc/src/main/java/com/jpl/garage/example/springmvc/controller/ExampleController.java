package com.jpl.garage.example.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpl.garage.example.springmvc.SimpleEnum;
import com.jpl.garage.example.springmvc.service.example.BulletinService;
import com.jpl.garage.example.springmvc.vo.page.BulletinInfoPageVO;
import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

@Controller
public class ExampleController {

	@Autowired
	private BulletinService service;
	
	@RequestMapping("/example/bulletin/list")
	public String showBulletin(Model model){
		String result = "/example/list";
		
		List<BbsDataBean> list = service.getBulletinList();
		model.addAttribute("bulletinList",list);
		
		return result;
	}
	
	@RequestMapping("/example/bulletin/pop/view")
	public String popBulletin(
			@ModelAttribute("bulletinInfo") BulletinInfoPageVO bulletinInfo){
		String result = "/example/pop/pop";
		if(SimpleEnum.update == bulletinInfo.getMode()){
			BbsDataBean bulletin = bulletinInfo.getBulletin();
			bulletin = service.getBulletinInfo(bulletin);
			bulletinInfo.setBulletin(bulletin);
		}
		return result;
	}
	
	@RequestMapping("/example/bulletin/pop/save")
	public String saveBulletin(
			@ModelAttribute("bulletinInfo") BulletinInfoPageVO bulletinInfo
			,Model model){

		String result = "/example/pop/pop";
		
		BbsDataBean bulletin = bulletinInfo.getBulletin();
		switch(bulletinInfo.getMode()){
		case insert:
			service.addBbsData(bulletin);
			break;
			
		case update:
			break;
		}
		bulletinInfo.setMode(SimpleEnum.update);
		model.addAttribute("message","successful");
		return result;
	}
}
