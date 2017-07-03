package edu.scau.service;

import edu.scau.mapper.UserMapper;
import edu.scau.model.User;
import edu.scau.util.BookstoreUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Shoulder on 6/28/2017.
 */
@Service
public class UserService {
	
    @Autowired
    private UserMapper userMapper;

    public Map<String, String> register(String username, String password) {
        Map<String, String> map = new HashMap<String, String>();

        //合法性检测
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userMapper.selectById(username);
        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }

        user = new User();
        user.setUserid(username);
        //MD5算法加密
        user.setPassword(BookstoreUtil.MD5(password));
        userMapper.addUser(user);

        map.put("Result Of Register", "注册成功");
        return map;
    }

    public Map<String, String> login(String username, String password) {
        Map<String, String> map = new HashMap<String, String>();

        //合法性检测
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userMapper.selectById(username);
        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        //MD5加密登录密码后与底层数据库作比较
        if (!BookstoreUtil.MD5(password).equals(user.getPassword())) {
            map.put("msg", "密码错误");
            return map;
        }

        map.put("Result Of Register", "注册成功");
        return map;
    }

    public User getUser(String id) {
        return userMapper.selectById(id);
    }
}
