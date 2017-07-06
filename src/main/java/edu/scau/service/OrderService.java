package edu.scau.service;

import edu.scau.mapper.OrderMapper;
import edu.scau.mapper.UserMapper;
import edu.scau.model.Order;
import edu.scau.model.User;
import edu.scau.util.BookstoreUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shoulder on 6/28/2017.
 */
@Service
public class OrderService {
	
    @Autowired
    private OrderMapper orderMapper;

    public Order selectLatestOrder(String userId, int offset, int limit) {
        return orderMapper.selectLatestOrder(userId, offset, limit);
    }
}
