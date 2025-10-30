package com.backend.jjj.cinema_api.specifications;

import com.backend.jjj.cinema_api.models.MoviesModel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MoviesSpecification {

    public static Specification<MoviesModel> titleContains(String title) {
        return (root, query, cb) ->
                (title == null || title.isBlank())
                        ? null
                        : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<MoviesModel> hasAnyGenre(List<String> genres) {
        return (root, query, cb) -> {
            if (genres == null || genres.isEmpty()) return null;

            // faz join com a coleção genres
            var join = root.join("genres");

            // cria um "WHERE genres IN (:genres)"
            return join.in(genres);
        };
    }

    public static Specification<MoviesModel> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("active"));
    }
}
