package com.icommerce.iproduct.handler.search;

import com.icommerce.iproduct.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<ProductEntity> {

    private SearchCriteria criteria;

    public ProductSpecification(final SearchCriteria searchCriteria) {
        super();
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation() == null) {
            return builder.like(root.get(criteria.getKey()), "*");
        }

        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return builder.like(root.get(criteria.getKey()), "*");
        }
    }
}
