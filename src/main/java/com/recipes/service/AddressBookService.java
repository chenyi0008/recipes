package com.recipes.service;

import com.recipes.entity.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookService {

    public void save(AddressBook addressBook);

    public List<AddressBook> getAll(Long userId);

    public Integer deleteById(Long id, Long userId);

    public AddressBook getById(Long id, Long userId);


}
