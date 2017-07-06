package edu.scau.service;

import edu.scau.mapper.OrderHasBookMapper;
import edu.scau.mapper.UserMapper;
import edu.scau.model.OrderHasBook;
import edu.scau.model.User;
import edu.scau.util.BookstoreUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shoulder on 6/28/2017.
 */
@Service
public class OrderHasBookService {
	
    @Autowired
    private OrderHasBookMapper orderHasBookMapper;

    public List<OrderHasBook> selectById(int id) {
        return orderHasBookMapper.selectById(id);
    }
}
