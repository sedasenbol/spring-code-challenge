package com.canovate.scc.model.data.search;

import com.canovate.scc.types.SearchOperation;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
}
