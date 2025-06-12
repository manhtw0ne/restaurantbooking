package com.example.restaurantbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // tạo tự động getter/setter/toString/equals/hashCode
@NoArgsConstructor // tạo constructor không có tham số
@AllArgsConstructor // tạo constructor có tất cả tham số
@Entity

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String date;
    private String time;
    private int numberOfPeople;
}