package com.icommerce.iproduct.handler.search;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum SearchOperation {
    EQUALITY,
    NEGATION,
    GREATER_THAN,
    LESS_THAN,
    LIKE,
    STARTS_WITH,
    ENDS_WITH,
    CONTAINS;

    public static final String[] SIMPLE_OPERATION_SET =
            {":", "!", ">", "<", "~"};
    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_PREDICATE_FLAG = "'";

    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            default:
                return null;
        }
    }

    private static Map<String, SearchOperation> mapping = new HashMap<>();

    static {
        mapping.put(":", EQUALITY);
        mapping.put("!", NEGATION);
        mapping.put(">", GREATER_THAN);
        mapping.put("<", LESS_THAN);
        mapping.put("~", LIKE);
    }

    @JsonCreator
    public static SearchOperation fromString(String value) {
        return mapping.get(value);
    }
}
