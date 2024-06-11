package com.tedkvn.erp.dao;


import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.UserStatus;
import org.hibernate.search.engine.search.query.SearchResult;

import java.util.List;

public interface SearchDao {

    SearchResult<User> searchUser(String keywords, int limit, int page, String sortBy,
                                  String sortByOrder, List<UserStatus> status);

}
