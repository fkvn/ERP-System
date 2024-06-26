package com.tedkvn.erp.service.search;


import com.tedkvn.erp.entity.user.UserStatus;
import com.tedkvn.erp.rest.response.SearchResponse;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;

import java.util.List;

public interface SearchService {

    SearchResponse<?> searchUser(String keywords, int limit, int page, List<String> sortBy,
                                 List<SortOrder> sortByOrder, List<UserStatus> status);
}
