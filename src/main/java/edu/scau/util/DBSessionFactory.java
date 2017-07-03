package edu.scau.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBSessionFactory {
	private static SqlSessionFactory sqlSessionFactory ;
	
	static{
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config-xml.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取sqlSession的方法.
	 * @return
	 */
	public static SqlSession openSession(){
		if(sqlSessionFactory!=null){
			SqlSession sqlSession = sqlSessionFactory.openSession();
			return sqlSession;
		}
		return null;
	}
	
}