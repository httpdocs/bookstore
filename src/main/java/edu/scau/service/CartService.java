package edu.scau.service;

import edu.scau.mapper.CartMapper;
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

    public List<String> getIsbns(String id){
        System.out.println("enter cartService method");
        return cartMapper.getIsbns(id);
    }
}
