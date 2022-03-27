package com.example.demo.repository;

import com.example.demo.entity.Request;
import com.example.demo.entity.User;
import com.example.demo.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends BaseRepository<Request, Long> {

    @Query(value = "select sum(r.amount) from Request r where r.user.id=?1")
    Optional<BigDecimal> getSumOstatok(Long id);

    @Query(value = "select sum(date_part('year', AGE(r.date_close, current_timestamp)) * 12 + date_part('month', AGE(r.date_close,current_timestamp)))+2" +
            "from request as r where r.user_id=?1", nativeQuery = true)
    Integer getPayment(Long id);

    @Query(value = "select r.date_open + interval '1 month' * ((date_part('year', AGE(current_timestamp, r.date_open)) * 12 +" +
            "date_part('month', AGE(current_timestamp, r.date_open))) + 1) " +
            "from request as r " +
            "where r.user_id = ?1", nativeQuery = true)
    List<Timestamp> getDates(Long id);

    List<Request> getByUser(User user);
}
