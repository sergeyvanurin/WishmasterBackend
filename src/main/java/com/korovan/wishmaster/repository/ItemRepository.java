package com.korovan.wishmaster.repository;

import com.korovan.wishmaster.domain.Item;
import com.korovan.wishmaster.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
}
