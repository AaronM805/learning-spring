package com.aaronmartinez.lil.learningspring.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION ")
public class Reservation {

    @Id
    @Column(name="RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

    @Column(name="ROOM_ID")
    private long roomId;

    @Column(name="GUEST_ID")
    private long guestId;
    
    @Column(name="RES_DATE")
    private Date resvervationDate;

    /**
     * @return the reservationId
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * @param reservationId the reservationId to set
     */
    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * @return the roomId
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the guestId
     */
    public long getGuestId() {
        return guestId;
    }

    /**
     * @param guestId the guestId to set
     */
    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    /**
     * @return the resvervationDate
     */
    public Date getResvervationDate() {
        return resvervationDate;
    }

    /**
     * @param resvervationDate the resvervationDate to set
     */
    public void setResvervationDate(Date resvervationDate) {
        this.resvervationDate = resvervationDate;
    }
}