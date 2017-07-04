package edu.scau.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.SearchMapper;
import edu.scau.mapper.view.SearchResult;
import edu.scau.util.JSONUtil;

@Service
public class SearchService {

	@Autowired
	private SearchMapper mapper;
	
	public String byCate(String cate){
		List<SearchResult> result = mapper.selectByCate(cate);
		JSONArray array = JSONUtil.listToArray(result, SearchResult.class);
		return array.toString();
	}
	
}
