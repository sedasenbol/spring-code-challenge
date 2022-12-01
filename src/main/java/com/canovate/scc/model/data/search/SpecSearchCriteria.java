package com.canovate.scc.model.data.search;

import com.canovate.scc.types.SearchOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecSearchCriteria extends SearchCriteria{

    private final String key;
    private final SearchOperation operation;
    private final Object value;
    private boolean orPredicate;

    public boolean isOrPredicate() {
        return orPredicate;
    }

    public SpecSearchCriteria(String orPredicate, String key, SearchOperation operation, Object value) {
        this.orPredicate = false;//!orPredicate.contains("&");
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SpecSearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = ExtractSearchOperation.getSimpleOperation(operation.charAt(0));
        this.value = value;
    }
}