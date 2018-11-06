package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.search.pojo.Item;
import com.jt.search.service.SearchService;

@Controller
public class SearchController {
//由dubbo来提供searchService
	@Autowired
	SearchService searchService;
	
	@RequestMapping("/search")
	public String seach(String q,Model model)throws Exception {
		
		byte[] data = q.getBytes("ISO-8859-1");
		String key = new String(data, "UTF-8");
		
		List<Item> itemList = searchService.findItemByKey(key);
		model.addAttribute("itemList", itemList);
		return "search";
	}
}
