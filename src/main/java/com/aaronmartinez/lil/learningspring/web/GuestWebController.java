package com.aaronmartinez.lil.learningspring.web;

import java.util.List;

import com.aaronmartinez.lil.learningspring.business.service.ReservationService;
import com.aaronmartinez.lil.learningspring.data.entity.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guests")
public class GuestWebController {
    private final ReservationService reservationService;

    /**
     * 
     * @param reservationService
     */
    @Autowired
    public GuestWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getGuests(Model model) {

        List<Guest> guests = this.reservationService.getGuests();
        model.addAttribute("guests", guests);

        return "guests";
    }
    
}

/*
Create a page to list all of the guests in DB. Sort in alphabetical order by last name.
    Use service abstraction already created and enhance API
    return domain level guest object instead of creating DTO
*/