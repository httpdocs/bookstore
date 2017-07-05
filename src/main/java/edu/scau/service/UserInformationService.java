package edu.scau.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.AddressMapper;
import edu.scau.mapper.UserMapper;
import edu.scau.model.Address;
import edu.scau.model.User;

@Service
public class UserInformationService {
	   @Autowired
       AddressMapper addressMapper;
	   @Autowired
	   UserMapper userMapper;
	   
	   private User getUser(String userid){
		   return userMapper.selectById(userid);
	   }
	   
	   private Address search(String userid,Address newAddress){
		   Integer addressId=getUser(userid).getDefaddr();
		   if(addressId!=null){
			   newAddress.setAddressid(addressId);
			   System.out.println("search查出的addressId："+addressId);
		   }
		   else{
			   System.out.println("search查不出的addressId");
		   }
		   return newAddress;
	   }
	   
	   public Address add(Address newAddress,String userid) throws Exception{
		   Address address=search(userid, newAddress);
		   if(address.getAddressid()!=null){
			   System.out.println("addressId已存在，请调用update操作");
			   throw new Exception("addressId已存在，请调用update操作");
		   }else{
			   addressMapper.insert(address);
			   System.out.println("address插入成功");
		   }
		return address;
	   }
	   
	   public Address update(Address newAddress,String userid) throws Exception{
		   Address address=search(userid, newAddress);
		   if(address.getAddressid()!=null){
			   addressMapper.updateByPrimaryKeySelective(address);
			   System.out.println("address更新成功");
		   }else{
			   System.out.println("addressId不存在，请调用add操作");
			   throw new Exception("addressId不存在，请调用add操作");
		   }
		return address;
	   }
	   
}
