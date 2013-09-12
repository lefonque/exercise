package com.jpl.garage.example.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jpl.garage.example.springmvc.SimpleEnum;
import com.jpl.garage.example.springmvc.service.example.BulletinService;
import com.jpl.garage.example.springmvc.vo.page.BulletinInfoPageVO;
import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

@Controller
public class ExampleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
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
			@ModelAttribute("bulletinInfo") BulletinInfoPageVO bulletinInfo
			,ModelMap model){
		String result = "/example/pop/pop";
		logger.debug("message : {}", model.get("message"));
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
			,RedirectAttributes attr
			,Model model){

		
		
		BbsDataBean bulletin = bulletinInfo.getBulletin();
		switch(bulletinInfo.getMode()){
		case insert:
			service.addBbsData(bulletin);
			break;
			
		case update:
			break;
		}
		bulletinInfo.setMode(SimpleEnum.update);
		
		// Take 1.
//		String result = "forward:/example/bulletin/pop/view";
//		model.addAttribute("bulletinInfo",bulletinInfo);
		
		// Take 2.
		String result = "redirect:/example/bulletin/pop/view";
		attr.addFlashAttribute("message","successful");
		attr.addFlashAttribute("bulletinInfo", bulletinInfo);
		
		return result;
	}
	
	
	@RequestMapping("/example/autocomplete")
	public String autocomplete(Model model){
		String result = "example/autocomplete";
		
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> one = new HashMap<String,String>();
		one.put("value","ID-A");
		one.put("label","Name-A");
		list.add(one);
		
		one = new HashMap<String,String>();
		one.put("value","ID-B");
		one.put("label","Name-B");
		list.add(one);
		
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("json", json);
		return result;
	}

	class TestData{
		private String label;
		private String value;
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
}
