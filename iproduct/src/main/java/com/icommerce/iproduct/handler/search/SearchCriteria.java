package com.icommerce.iproduct.handler.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean isOrPredicate;


    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String isOrPredicate, String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.isOrPredicate = isOrPredicate != null && isOrPredicate.equals(SearchOperation.OR_PREDICATE_FLAG);
    }

    public SearchCriteria(String key, String operation, String prefix, String value, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
        }
        this.key = key;
        this.operation = op;
        this.value = value;
    }
}
