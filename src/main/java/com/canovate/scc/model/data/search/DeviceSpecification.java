package com.canovate.scc.model.data.search;

import com.canovate.scc.model.Device;
import com.canovate.scc.types.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DeviceSpecification implements Specification<Device> {

    public DeviceSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }

    private final SpecSearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_OR_EQUAL_TO)) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_OR_EQUAL_TO)) {
            return builder.lessThanOrEqualTo(
            root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
