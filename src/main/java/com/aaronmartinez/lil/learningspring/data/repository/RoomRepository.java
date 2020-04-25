package com.aaronmartinez.lil.learningspring.data.repository;

import com.aaronmartinez.lil.learningspring.data.entity.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}