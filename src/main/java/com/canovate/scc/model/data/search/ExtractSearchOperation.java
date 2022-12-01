package com.canovate.scc.model.data.search;

import com.canovate.scc.types.SearchOperation;

public class ExtractSearchOperation {

    public static SearchOperation getSimpleOperation(Character c){

        return switch (c) {
            case '=' -> SearchOperation.EQUALITY;
            case '.' -> SearchOperation.STARTS_WITH;
            case '*' -> SearchOperation.ENDS_WITH;
            case ':' -> SearchOperation.EQUALITY;
            default -> SearchOperation.EQUALITY;
        };
    }
}
