package com.fitbook.repository;

import com.fitbook.dto.SearchDto;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TrainerSpecification {

    public static Specification<Trainer> findTrainers(SearchDto searchDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchDto.getName() != null && !searchDto.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("name"), searchDto.getName()));
            }

            if (searchDto.getGender() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), searchDto.getGender()));
            }

            if (searchDto.getCity() != null && !searchDto.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("city"), searchDto.getCity()));
            }

            if (searchDto.getNeighborhood() != null && !searchDto.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("neighborhood"), searchDto.getNeighborhood()));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
