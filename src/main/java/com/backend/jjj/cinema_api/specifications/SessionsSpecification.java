package com.backend.jjj.cinema_api.specifications;

import org.springframework.data.jpa.domain.Specification;
import com.backend.jjj.cinema_api.models.SessionsModel;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionsSpecification {

    public static Specification<SessionsModel> hasMovieId(String movieId) {
        return (root, query, cb) ->
                cb.equal(root.get("movie").get("movieId"), movieId);
    }

    public static Specification<SessionsModel> hasDate(LocalDate date) {
        if (date == null) return null;
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        return (root, query, cb) ->
                cb.between(root.get("startTime"), startOfDay, endOfDay);
    }

    public static Specification<SessionsModel> isActive() {
        return (root, query, cb) -> {
            LocalDateTime now = LocalDateTime.now();
            return cb.greaterThan(root.get("endTime"), now);
        };
    }
}
