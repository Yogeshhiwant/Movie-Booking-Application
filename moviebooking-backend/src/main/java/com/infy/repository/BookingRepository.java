package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Booking;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
	 @Query("select b from Booking b where b.user.userId=:userId")
      List<Booking> findByUserId(@Param("userId") String userId);
}
