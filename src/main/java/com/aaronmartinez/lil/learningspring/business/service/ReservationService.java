package com.aaronmartinez.lil.learningspring.business.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aaronmartinez.lil.learningspring.business.domain.RoomReservation;
import com.aaronmartinez.lil.learningspring.data.entity.Guest;
import com.aaronmartinez.lil.learningspring.data.entity.Reservation;
import com.aaronmartinez.lil.learningspring.data.entity.Room;
import com.aaronmartinez.lil.learningspring.data.repository.GuestRepository;
import com.aaronmartinez.lil.learningspring.data.repository.ReservationRepository;
import com.aaronmartinez.lil.learningspring.data.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    Will be component scanned.

    @Service is a stereotype of @Component, which allows component scanning to occur.
    @Service is used to set transaction boundaries and log boundaries and by using @Service annotation, aspects can be built
    against this that won't apply to other classes within the stack.

    If you use @Component, it will also work.
*/
@Service
public class ReservationService {
    // final is used so that once they are set, they don't change.
    // this is good OO Practices
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    /**
     * @param roomRepository
     * @param guestRepository
     * @param reservationRepository
     */
    /* This is technically not required, since there is only one constructor. Spring will use it by default, and assume it has
        all of the beans defined in the application context.
        
        Some benefits by specifiying this on Constructor:
            1. IDE is smart enough to know if bean has been defined in application context that would run with application. It
                will give warning that it doesn't know of bean type
            2. other benefit is that it is very clear what constructor is being called at any point in time. If someone comes in
                and adds another constructor, they would have to make the change to make that the autowired constructor.*/
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
            ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        // Focus on Spring and not Java in this code. THere is probably a bettter way to do this in Java.
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.
                                            findReservationByReservationDate(new java.sql.Date(date.getTime()));
        
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);

            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }

        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if(o1.getRoomName() == o2.getRoomName()) {
                    return o1.getRoomName().compareTo(o2.getRoomName());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });

        return roomReservations;
    }
}