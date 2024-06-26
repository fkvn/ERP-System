package com.tedkvn.erp.dao;


import com.tedkvn.erp.entity.user.User;
import com.tedkvn.erp.entity.user.UserStatus;
import com.tedkvn.erp.rest.exception.BadRequest;
import jakarta.persistence.EntityManager;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class SearchDaoImpl implements SearchDao {
    //
    @Autowired
    private EntityManager entityManager;

    @Override
    public SearchResult<User> searchUser(String keywords, int limit, int page, List<String> sortBy,
                                         List<SortOrder> sortOrder, List<UserStatus> status) {

        SearchSession searchSession = Search.session(entityManager);

        List<UserStatus> finalStatus = Optional.ofNullable(status)
                .orElse(Arrays.asList(UserStatus.ACTIVE, UserStatus.PENDING_ACTIVATION,
                        UserStatus.INACTIVE, UserStatus.LOCKED));

        return searchSession.search(User.class).where(f -> f.bool(b -> {


            if (!keywords.isEmpty()) {
                b.must(f.phrase().field("userCode").boost(2.0f).field("username").boost(2.0f)
                        .field("fullName").boost(1.0f).field("email").boost(1.0f).field("phone")
                        .boost(1.0f).matching(keywords));
            }

            //	status filter
            b.filter(f.terms().field("status").matchingAny(finalStatus));

        })).sort(f -> f.composite(b -> {
            if (sortBy != null && sortOrder != null) {
                for (int i = 0; i < sortBy.size(); i++) {
                    String field = sortBy.get(i);
                    SortOrder order = i < sortOrder.size() ? sortOrder.get(i) : SortOrder.DESC;
                    try {
                        b.add(f.field(field).order(order));
                    } catch (Exception e) {
                        throw new BadRequest(field + " is not sortable at the moment!");
                    }
                }
            }
            // stabilize the sort even further by adding a last sort on the id.
            b.add(f.field("id").order(SortOrder.ASC));

        })).fetch(limit * (page - 1), limit);
    }


}


