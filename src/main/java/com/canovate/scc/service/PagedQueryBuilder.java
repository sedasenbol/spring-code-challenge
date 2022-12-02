package com.canovate.scc.service;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class PagedQueryBuilder {

    public static String build(String query, String alias, Pageable pageable) {
        final StringBuilder queryStr = new StringBuilder();
        queryStr.append("select * from ( " + query + " ) " + alias);
        if (pageable.getSort() != null && pageable.getSort().isSorted()) {
            queryStr.append(" order by ");
            String sort = pageable.getSort().get().map(order -> order.getProperty() + " " + order.getDirection().name()).collect(Collectors.joining(", "));
            queryStr.append(sort);
        }
        queryStr.append(" LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset());

        return queryStr.toString();
    }

    public static String buildCount(String query, String alias) {
        final StringBuilder queryStr = new StringBuilder();
        queryStr.append("select count(1) from ( " + query + " ) " + alias);

        return queryStr.toString();
    }

    public static String buildOrderBy(String query, Pageable pageable) {
        final StringBuilder queryStr = new StringBuilder();
        queryStr.append(query);

        if (pageable.getSort() != null && pageable.getSort().isSorted()) {
            queryStr.append(" order by ");
            List<Sort.Order> collect = pageable.getSort().get().collect(Collectors.toList());
            String property = collect.get(0).getProperty();
            if (property.equals("description")) {
                queryStr.append("ci.");
            } else if (property.equals("id") || property.equals("cargomaticId")) {
                queryStr.append("la.clientId.");
            } else {
                queryStr.append("la.");
            }
            String sort = pageable.getSort().get().map(order -> order.getProperty() + " " + order.getDirection().name()).collect(Collectors.joining(", "));
            queryStr.append(sort);
        }
        return queryStr.toString();
    }
}
