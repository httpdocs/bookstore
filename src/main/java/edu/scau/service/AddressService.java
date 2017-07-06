package edu.scau.service;

import edu.scau.mapper.AddressMapper;
import edu.scau.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Shoulder on 7/5/2017.
 */
@Service
public class AddressService {

@Autowired
    AddressMapper addressMapper;
    public Address getAddress(int id) {
        return addressMapper.selectById(id);
    }
}
