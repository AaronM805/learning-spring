package com.aaronmartinez.lil.learningspring.web;

import java.util.Date;
import java.util.List;

import com.aaronmartinez.lil.learningspring.business.domain.RoomReservation;
import com.aaronmartinez.lil.learningspring.business.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/** pass in reservations url. What this tells the Spring ApplicationContext is when this class gets built it'll serve the
 * mapping so that /reservations off of the root application goes to this controller and then we do our class as we need to
*/
@RequestMapping("/reservations")
public class RoomReservationWebController {
    private final ReservationService reservationService;

    /**
     * @param reservationService
     */
    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /* what this does is it says, at the request mapping URL, any get method will be responded to by this method.*/
    @GetMapping
    public String getReservations(@RequestParam(value="date", required = false) String dateString, Model model) {
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);

        model.addAttribute("roomReservations", roomReservations);
        return "reservations";
    }


}