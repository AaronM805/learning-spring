package com.aaronmartinez.lil.learningspring.data.repository;

import java.sql.Date;

import com.aaronmartinez.lil.learningspring.data.entity.Reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    /* Create a method
        Spring data will build the query for us based on the name of the method we are creating.*/
    Iterable<Reservation> findReservationByReservationDate(Date date);
}