package com.example.restaurantbooking.controller;

import com.example.restaurantbooking.model.Booking;
import com.example.restaurantbooking.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // 🔓 Cho cả USER và ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // 🔓 Cho cả USER và ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    // 🔓 Cho cả USER và ADMIN
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id);
    }

    // 🔐 CHỈ ADMIN được xóa
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }

    // 🔐 CHỈ ADMIN được sửa
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") // sửa lại path cho đúng
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setCustomerName(updatedBooking.getCustomerName());
                    booking.setDate(updatedBooking.getDate());
                    booking.setNumberOfPeople(updatedBooking.getNumberOfPeople());
                    Booking savedBooking = bookingRepository.save(booking);
                    return ResponseEntity.ok(savedBooking);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

