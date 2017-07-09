package edu.scau.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.BookMapper;
import edu.scau.mapper.PictureMapper;
import edu.scau.model.Picture;
import edu.scau.util.DBSessionFactory;

@Service
public class PictureService {
	
	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * 图片上传
	 * @param path
	 * @return
	 */
	public int upload(String path,String isbn){
		Picture pic = new Picture();
		pic.setPath(path);
		SqlSession session = DBSessionFactory.openSession();
		PictureMapper mapper = session.getMapper(PictureMapper.class);
		try{
			mapper.insert(pic);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		bookMapper.updatePic(isbn, pic.getPictureid());
		return pic.getPictureid();
	}
	
	/**
	 * 获取图片，返回文件字节流
	 * @param picture
	 * @return
	 */
	public InputStream get(Picture picture){
		SqlSession session = DBSessionFactory.openSession();
		PictureMapper mapper = session.getMapper(PictureMapper.class);
		picture = mapper.selectByPrimaryKey(picture.getPictureid());
		if(picture == null){
			return null;
		}
		try {
			FileInputStream in = new FileInputStream(picture.getPath());
			return in;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 图片绑定图书
	 */
	public int bind(Picture picture){
		SqlSession session = DBSessionFactory.openSession();
		PictureMapper mapper = session.getMapper(PictureMapper.class);
		picture = mapper.selectByPrimaryKey(picture.getPictureid());
		if(picture == null){
			return -3;
		}
		if(picture.getIsbn() != null || picture.getIsbn().equals("")){
			return -2;
		}
		try {
			mapper.updateByPrimaryKeySelective(picture);
			session.commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 删除图片
	 * @param picture
	 * @return
	 */
	public int delete(Picture picture){
		SqlSession session = DBSessionFactory.openSession();
		PictureMapper mapper = session.getMapper(PictureMapper.class);
		picture = mapper.selectByPrimaryKey(picture.getPictureid());
		if(picture == null){
			return -3;
		}
		try {
			mapper.deleteByPrimaryKey(picture.getPictureid());
			session.commit();
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}
	
}
