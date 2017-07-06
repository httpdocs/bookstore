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
	
	/**
	 * 分类下所有图书
	 * @param cate
	 * @return
	 */
	public String byCate(String cate){
		List<SearchResult> result = mapper.selectByCate(cate);
		JSONArray array = JSONUtil.listToArray(result, SearchResult.class);
		return array.toString();
	}
	
	/**
	 * 根据关键字搜索
	 * @param key
	 * @return
	 */
	public String byKey(String key){
		List<SearchResult> result = mapper.selectByKey(key);
		JSONArray array = JSONUtil.listToArray(result, SearchResult.class);
		return array.toString();
	}
	
}
