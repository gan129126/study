package com.jt.search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.search.pojo.Item;

//微服务提供者
public class SearchServiceImpl implements SearchService{

	@Autowired
	HttpSolrServer httpSolrServer;
	public List<Item> findItemByKey(String key) {
		List<Item> itemList = null;
		try {
			SolrQuery solrQuery = new SolrQuery(key);
			solrQuery.setStart(0);
			solrQuery.setRows(20);
			QueryResponse response = httpSolrServer.query(solrQuery);
			//Item.class是类信息，通过类信息得到属性Field.getAnnation()
			itemList = response.getBeans(Item.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return itemList;
	}

}
