package com.aaronmartinez.lil.learningspring;

import com.aaronmartinez.lil.learningspring.data.entity.Guest;
import com.aaronmartinez.lil.learningspring.data.entity.Reservation;
import com.aaronmartinez.lil.learningspring.data.entity.Room;
import com.aaronmartinez.lil.learningspring.data.repository.GuestRepository;
import com.aaronmartinez.lil.learningspring.data.repository.ReservationRepository;
import com.aaronmartinez.lil.learningspring.data.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Anti-Pattern. Will not live very long. DON'T DO THIS IN PROD
	@RestController
	@RequestMapping("/rooms")
	public class RoomController {
		@Autowired
		private RoomRepository roomRepository;

		@GetMapping
		public Iterable<Room> getRooms() {
			return this.roomRepository.findAll();	
		}
	}

	@RestController
	@RequestMapping("/reservations")
	public class ReservationController {
		@Autowired
		private ReservationRepository reservationRepository;

		@GetMapping
		public Iterable<Reservation> getReservations() {
			return this.reservationRepository.findAll();
		}
	}

	@RestController
	@RequestMapping("/guests")
	public class GuestController {
		@Autowired
		private GuestRepository guestRepository;

		@GetMapping
		public Iterable<Guest> getGuests() {
			return this.guestRepository.findAll();
		}
	}
}