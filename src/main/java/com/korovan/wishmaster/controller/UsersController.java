package com.korovan.wishmaster.controller;

import com.korovan.wishmaster.domain.Item;
import com.korovan.wishmaster.domain.User;
import com.korovan.wishmaster.objects.*;
import com.korovan.wishmaster.repository.ItemRepository;
import com.korovan.wishmaster.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/login")
    public ResponseAuth authenticate(@RequestBody RequestAuth auth) {
        User currentUser = userRepository.findUserByName(auth.name);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        ResponseAuth response = new ResponseAuth();
        if (currentUser != null && encoder.matches(auth.password, currentUser.getPassword())) {
            response.name = currentUser.getName();
            response.id = currentUser.getUser_id();
        }

        return response;
    }

    @PostMapping("/register")
    public ResponseAuth register(@RequestBody RequestAuth auth) {
        User currentUser = userRepository.findUserByName(auth.name);
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        ResponseAuth response = new ResponseAuth();
        if (currentUser != null) {
            return response;
        }
        User newUser = new User();
        response.name = auth.name;
        response.id = newUser.getUser_id();

        newUser.setPassword(encoder.encode(auth.password));
        newUser.setName(auth.name);
        userRepository.save(newUser);
        return response;
    }


    @PostMapping("/get_id")
    public ResponseId getId(@RequestBody RequestGetId name) {
        User currentUser = userRepository.findUserByName(name.name);
        ResponseId response = new ResponseId();
        response.id = currentUser.getUser_id();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseUser getUser(@PathVariable Long id) {
        User currentUser =  userRepository.findById(id).orElseThrow(RuntimeException::new);
        List<Item> currentItems = itemRepository.findByUser(currentUser);

        return new ResponseUser(currentUser, currentItems);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
        User savedUser = userRepository.save(user);

        return ResponseEntity.created(new URI("/users/" + savedUser.getUser_id())).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setName(user.getName());
        currentUser = userRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity addItem(@PathVariable Long id, @RequestBody ResponseItem item) {
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        Item new_item = new Item(item.name, item.url, item.img_url, item.price, currentUser);
        currentUser.addItem(new_item);
        userRepository.save(currentUser);

        return ResponseEntity.ok(item);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity removeItem(@PathVariable Long id, @RequestBody ResponseItem item) {
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        Item item_to_remove = new Item(item.name, item.url, item.img_url, item.price, currentUser);
        currentUser.removeItem(item_to_remove);
        userRepository.save(currentUser);

        return ResponseEntity.ok(item);
    }
}
