package com.crudapi.crud.specification;

import com.crudapi.crud.enums.OrderStatus;
import com.crudapi.crud.model.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {
    public static Specification<Order> filterOrder(
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime creationDateTime,
            OrderStatus status
    ) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (creationDateTime != null) {
                predicates.add(criteriaBuilder.between(root.get("creationDateTime"), startDate, endDate));
            }

            if(status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
