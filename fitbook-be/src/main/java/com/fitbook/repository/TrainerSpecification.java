package com.fitbook.repository;

import com.fitbook.dto.SearchDto;
import com.fitbook.entity.trainer.Trainer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TrainerSpecification {

    public static Specification<Trainer> findTrainers(SearchDto searchDto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchDto.getName() != null && !searchDto.getName().equals("")) {
                String[] tokens = searchDto.getName().split(" ");
                List<Predicate> namePredicates = new ArrayList<>();
                for (String token : tokens) {
                    namePredicates.add(cb.equal(root.get("firstName"), token));
                    namePredicates.add(cb.equal(root.get("lastName"), token));
                }
                predicates.add(cb.or(namePredicates.toArray(Predicate[]::new)));
            }

            if (searchDto.getGender() != null) {
                predicates.add(cb.equal(root.get("gender"), searchDto.getGender()));
            }

            if (searchDto.getCity() != null && !searchDto.getCity().equals("")) {
                predicates.add(cb.equal(root.get("city"), searchDto.getCity()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
