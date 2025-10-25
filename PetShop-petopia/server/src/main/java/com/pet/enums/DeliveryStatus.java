package com.pet.enums;


public enum DeliveryStatus {
    PREPARING, // Đang chuẩn bị vận chuyển
    SHIPPED, // Đã vận chuyển từ kho
    IN_TRANSIT, // Đang vận chuyển
    DELIVERED, // Đã giao hàng
    RETURNED, // Đã trả hàng
    FAILED // Giao hàng thất bại
}