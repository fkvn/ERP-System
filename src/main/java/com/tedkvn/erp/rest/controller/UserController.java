package com.tedkvn.erp.rest.controller;

import com.tedkvn.erp.annotation.IsSuperAdmin;
import com.tedkvn.erp.entity.UserStatus;
import com.tedkvn.erp.repository.UserRepository;
import com.tedkvn.erp.rest.response.SearchResponse;
import com.tedkvn.erp.service.search.SearchService;
import com.tedkvn.erp.service.user.UserService;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @IsSuperAdmin
    //       keywords: Username, full name, email, phone number
    //       filter: status, company
    public SearchResponse<?> findAllUsers(
            @RequestParam(required = true, defaultValue = "") String keywords,
            @RequestParam(required = false) List<UserStatus> status,
            @RequestParam(required = false) List<String> sortBy,
            @RequestParam(required = false) List<SortOrder> sortByOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit) {

        return searchService.searchUser(keywords, limit, page, sortBy, sortByOrder, status);
    }

    @GetMapping("/status")
    public List<UserStatus> findUserStatusAll() {
        return List.of(UserStatus.class.getEnumConstants());
    }


}
