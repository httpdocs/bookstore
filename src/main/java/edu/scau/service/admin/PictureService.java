package edu.scau.service.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import edu.scau.mapper.PictureMapper;
import edu.scau.model.Picture;
import edu.scau.util.DBSessionFactory;

@Service
public class PictureService {
	
	public int upload(String path){
		Picture pic = new Picture();
		pic.setPath(path);
		SqlSession session = DBSessionFactory.openSession();
		PictureMapper mapper = session.getMapper(PictureMapper.class);
		try{
			mapper.insert(pic);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return pic.getPictureid();
	}
	
}
