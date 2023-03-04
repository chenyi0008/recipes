package com.recipes.mapper;

import com.recipes.entity.AddressBook;
import com.recipes.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AddressBookDao extends JpaRepository<AddressBook,Long>, JpaSpecificationExecutor<AddressBook> {

    List<AddressBook> findAddressBookByUserId(Long userId);

    Integer deleteAddressBookByIdAndUserId(Long id, Long userId);

}
