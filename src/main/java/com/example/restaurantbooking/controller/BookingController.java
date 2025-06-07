package com.example.restaurantbooking.controller;

import com.example.restaurantbooking.model.Booking;
import com.example.restaurantbooking.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@CrossOrigin // Cho phép gọi từ frontend
public class BookingController {


    @Autowired
    private BookingRepository bookingRepository;

    // Lấy tất cả booking
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Tạo mới một booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    // Lấy 1 booking theo id
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id);
    }

    // Xóa 1 booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }

}
