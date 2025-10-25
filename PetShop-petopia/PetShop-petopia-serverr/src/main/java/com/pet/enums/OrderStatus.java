package com.pet.enums;


public enum OrderStatus {
    PENDING, // Đơn hàng đã đặt nhưng chưa xác nhận
    CONFIRMED, // Đơn hàng đã được xác nhận bởi hệ thống hoặc admin
    SHIPPED, // Đơn hàng đã được vận chuyển
    DELIVERED, // Đơn hàng đã giao đến khách hàng
    CANCELLED // Đơn hàng đã bị hủy
}