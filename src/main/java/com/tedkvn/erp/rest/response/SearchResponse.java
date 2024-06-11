package com.tedkvn.erp.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse<T> {
    private long totalPage;

    private long totalCount;

    private List<T> fetchResult;
}
