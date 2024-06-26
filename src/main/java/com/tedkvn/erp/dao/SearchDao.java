package com.tedkvn.erp.dao;


import com.tedkvn.erp.entity.user.User;
import com.tedkvn.erp.entity.user.UserStatus;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;

import java.util.List;

public interface SearchDao {

    SearchResult<User> searchUser(String keywords, int limit, int page, List<String> sortBy,
                                  List<SortOrder> sortByOrder, List<UserStatus> status);

}
