package com.canovate.scc.model.data.search;

import com.canovate.scc.model.Device;
import com.canovate.scc.types.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DeviceSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public DeviceSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public DeviceSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SpecSearchCriteria(key, operation, value));
        return this;
    }

    public final DeviceSpecificationsBuilder with(String key, String operation, Object value,
                                                String prefix, String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final DeviceSpecificationsBuilder with(String orPredicate, String key, String operation,
                                                Object value, String prefix, String suffix) {
        SearchOperation op = ExtractSearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                boolean startWithAsterisk = prefix != null &&
                        prefix.contains("*");
                boolean endWithAsterisk = suffix != null &&
                        suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Device> build() {
        if (params.size() == 0)
            return null;

        Specification<Device> result = new DeviceSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new DeviceSpecification(params.get(i)))
                    : Specification.where(result).and(new DeviceSpecification(params.get(i)));
        }

        return result;
    }
}
