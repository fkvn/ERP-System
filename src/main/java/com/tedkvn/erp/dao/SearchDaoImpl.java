package com.tedkvn.erp.dao;


import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.UserStatus;
import jakarta.persistence.EntityManager;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.FieldSortOptionsStep;
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
    public SearchResult<User> searchUser(String keywords, int limit, int page, String sortBy,
                                         String sortByOrder, List<UserStatus> status) {

        SearchSession searchSession = Search.session(entityManager);

        List<UserStatus> finalStatus = Optional.ofNullable(status)
                .orElse(Arrays.asList(UserStatus.ACTIVE, UserStatus.PENDING_ACTIVATION,
                        UserStatus.INACTIVE, UserStatus.LOCKED));

        return searchSession.search(User.class).where(f -> f.bool(b -> {

            if (keywords != null && keywords.isEmpty()) {
                b.must(f.phrase().field("userCode").boost(3.0f).field("username").boost(2.0f)
                        .field("fullName").boost(1.0f).field("email").boost(1.0f).field("phone")
                        .boost(1.0f).matching(keywords));
            }

            //	status filter
            b.filter(f.terms().field("status").matchingAny(finalStatus));

        })).sort(f -> f.composite(b -> {
            FieldSortOptionsStep<?, ? extends SearchPredicateFactory> field = null;
            switch (sortBy) {
                case "createdOn": {
                    field = f.field("createdOn");
                    break;
                }
                case "updatedOn": {
                    field = f.field("updatedOn");
                    break;
                }
                default:
                    break;
            }
            if (field != null) {
                if (sortByOrder.equals("ascend")) field.order(SortOrder.ASC);
                else field.order(SortOrder.DESC);

                b.add(field);
            }
        })).totalHitCountThreshold(500).fetch(limit * (page - 1), limit);
    }
}
