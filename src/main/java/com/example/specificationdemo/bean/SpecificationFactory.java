package com.example.specificationdemo.bean;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationFactory {
    public static Specification contains(String attribute, String value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(attribute), "%" + value + "%");
    }

    public static Specification greaterThanOrEqualTo(String attribute, int value) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), value);
    }
}
