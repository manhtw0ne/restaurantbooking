package com.example.restaurantbooking;

import com.example.restaurantbooking.model.Role;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.restaurantbooking.model.User;
import com.example.restaurantbooking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestaurantBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantBookingApplication.class, args);
    }

    // Tạo dữ liệu mẫu
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User(null, "admin", passwordEncoder.encode("admin123"), Role.ADMIN));
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new User(null, "user", passwordEncoder.encode("user123"), Role.CUSTOMER));
            }
        };
    }
}
