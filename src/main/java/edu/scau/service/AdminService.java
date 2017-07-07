package edu.scau.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import edu.scau.mapper.AdminMapper;
import edu.scau.mapper.BookMapper;
import edu.scau.model.Admin;
import edu.scau.util.DBSessionFactory;
import edu.scau.util.JSONUtil;

@Service
public class AdminService {
	
	@Autowired
	private AdminMapper mapper;
	
	/**
	 * 鐧婚檰
	 * @param admin
	 * @return
	 */
	public Map<String, Object> login(Admin admin){
		SqlSession session = DBSessionFactory.openSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		Admin a = mapper.selectByPrimaryKey(admin.getAdminid());
		Map<String, Object> map = new HashMap<>();
		if(a == null){
			map.put("status", -2);
			map.put("msg", "鐢ㄦ埛涓嶅瓨鍦�");
		} else if(!a.getPassword().equals(admin.getPassword())){
			map.put("status", -3);
			map.put("msg", "瀵嗙爜閿欒");
		} else {
			map.put("admin", a.getAdminid());
			map.put("name", a.getName());
			map.put("status", 0);
			map.put("msg", "pass");
		}
		return map;
	}
	
	/**
	 * 鏂板绠＄悊鍛�
	 * @param admin
	 * @return
	 */
	public String add(Admin admin){
		SqlSession session = DBSessionFactory.openSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		Admin adminA = mapper.selectByPrimaryKey(admin.getAdminid());
		JSONObject json = new JSONObject();
		if(adminA != null){
			json.put("status", -2);
			json.put("msg", "璇ョ鐞嗗憳宸插瓨鍦�");
		} else {
			try{
				System.err.println(admin.getAdminid()+"name"+admin.getName()+"pwd"+admin.getPassword()+"au:"+admin.getAuthority());
				mapper.insertSelective(admin.getAdminid(),admin.getName(),admin.getPassword());
				session.commit();
				json.put("status", 0);
				json.put("msg", "娣诲姞鎴愬姛");
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", -1);
				json.put("msg", "娣诲姞澶辫触");
			}
		}
		return json.toString();
	}
	
	/**
	 * 绉婚櫎绠＄悊鍛�
	 * @param admin
	 * @return
	 */
	public String delete(Admin admin){
		SqlSession session = DBSessionFactory.openSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		admin = mapper.selectByPrimaryKey(admin.getAdminid());
		JSONObject json = new JSONObject();
		if(admin == null){
			json.put("status", -2);
			json.put("msg", "璇ョ鐞嗗憳涓嶅瓨鍦�");
		} else {
			try{
				mapper.deleteByPrimaryKey(admin.getAdminid());
				session.commit();
				json.put("status", 0);
				json.put("msg", "绉婚櫎鎴愬姛");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "绉婚櫎澶辫触");
			}
		}
		return json.toString();
	}
	
	
	/**
	 * 选择用户列表
	 * @return 返回json格式的字符串
	 */
	public String list(){
		//非注解的方式获取mapper.如果要自动注入的话，应该在mapper接口处声明@mapper
		//SqlSession session = DBSessionFactory.openSession();
		//AdminMapper mapper = session.getMapper(AdminMapper.class);
		List<Admin> adminList = mapper.selectAll();
		return JSONUtil.listToArray(adminList, Admin.class).toString();
	}

	public String get(String adminid) {
		Admin admin = mapper.selectByPrimaryKey(adminid);
		JSONObject jsonObject = new JSONObject(admin);
		return jsonObject.toString();
	}
	
	public JSONObject update(Admin admin){
		JSONObject json = new JSONObject();
		Admin cAdmin = mapper.selectByPrimaryKey(admin.getAdminid());
		if (cAdmin == null) {
			json.put("status", -2);
			json.put("msg", "该管理员不存在");
		}
		try {
			mapper.updateByPrimaryKey(admin);
			json.put("status", 0);
			json.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "操作失败");
		}
		return json;
	}
	
}
