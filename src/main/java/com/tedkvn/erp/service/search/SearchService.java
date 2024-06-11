package com.tedkvn.erp.service.search;


import com.tedkvn.erp.entity.UserStatus;
import com.tedkvn.erp.rest.response.SearchResponse;

import java.util.List;

public interface SearchService {
    
    SearchResponse<?> searchUser(String keywords, int limit, int page, String sortBy,
                                 String sortByOrder, List<UserStatus> status);
}
