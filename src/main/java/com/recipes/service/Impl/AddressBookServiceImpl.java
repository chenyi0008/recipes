package com.recipes.service.Impl;

import com.recipes.entity.AddressBook;
import com.recipes.mapper.AddressBookDao;
import com.recipes.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    AddressBookDao addressBookDao;

    @Override
    public void save(AddressBook addressBook) {
        addressBookDao.save(addressBook);
    }


    @Override
    public List<AddressBook> getAll(Long userId) {
        return addressBookDao.findAddressBookByUserId(userId);
    }

    @Override
    public Integer deleteById(Long id, Long userId) {
        return addressBookDao.deleteAddressBookByIdAndUserId(id, userId);
    }


}
