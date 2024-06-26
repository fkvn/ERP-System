package com.tedkvn.erp.service.search;

import com.tedkvn.erp.dao.SearchDao;
import com.tedkvn.erp.entity.user.User;
import com.tedkvn.erp.entity.user.UserStatus;
import com.tedkvn.erp.rest.response.SearchResponse;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResponse<?> searchUser(String keywords, int limit, int page, List<String> sortBy,
                                        List<SortOrder> sortOrder, List<UserStatus> status) {
        SearchResult<User> result =
                searchDao.searchUser(keywords, limit, page, sortBy, sortOrder, status);

        SearchResponse<User> searchRes = new SearchResponse<>();
        searchRes.setTotalCount(result.total().hitCount());
        searchRes.setTotalPage((searchRes.getTotalCount() / limit) + 1);
        searchRes.setFetchResult(result.hits());

        return searchRes;
    }
}
