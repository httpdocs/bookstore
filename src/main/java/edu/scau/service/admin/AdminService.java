package edu.scau.service.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import edu.scau.mapper.AdminMapper;
import edu.scau.model.Admin;
import edu.scau.util.DBSessionFactory;

@Service
public class AdminService {

	/**
	 * 登陆
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
			map.put("msg", "用户不存在");
		} else if(!a.getPassword().equals(admin.getPassword())){
			map.put("status", -3);
			map.put("msg", "密码错误");
		} else {
			map.put("admin", a.getAdminid());
			map.put("name", a.getName());
			map.put("status", 0);
			map.put("msg", "pass");
		}
		return map;
	}
	
	/**
	 * 新增管理员
	 * @param admin
	 * @return
	 */
	public String add(Admin admin){
		SqlSession session = DBSessionFactory.openSession();
		AdminMapper mapper = session.getMapper(AdminMapper.class);
		admin = mapper.selectByPrimaryKey(admin.getAdminid());
		JSONObject json = new JSONObject();
		if(admin != null){
			json.put("status", -2);
			json.put("msg", "该管理员已存在");
		} else {
			try{
				mapper.insert(admin);
				session.commit();
				json.put("status", 0);
				json.put("msg", "添加成功");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "添加失败");
			}
		}
		return json.toString();
	}
	
	/**
	 * 移除管理员
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
			json.put("msg", "该管理员不存在");
		} else {
			try{
				mapper.deleteByPrimaryKey(admin.getAdminid());
				session.commit();
				json.put("status", 0);
				json.put("msg", "移除成功");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "移除失败");
			}
		}
		return json.toString();
	}
	
}
