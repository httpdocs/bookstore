package edu.scau.service;

import edu.scau.mapper.CartMapper;
import edu.scau.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shoulder on 7/4/2017.
 */
@Service
public class CartService {
	@Autowired
	private CartMapper cartMapper;

	public List<String> getIsbns(String id) {

		return cartMapper.getIsbns(id);
	}

	public boolean Add(String isbn, String userId) {
		Cart cart = cartMapper.selectOne(userId, isbn);
		try {

			if (cart == null) {
				cart = new Cart();
				cart.setIsbn(isbn);
				cart.setUserid(userId);
				cart.setQuantity(1);
				cartMapper.insert(cart);
			} else {
				cart.setQuantity(cart.getQuantity() + 1);
				cartMapper.updateByPrimaryKey(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
