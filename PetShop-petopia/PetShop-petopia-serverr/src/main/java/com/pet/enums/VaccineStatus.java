package com.pet.enums;

public enum VaccineStatus {
    Da_TIEM("Đã tiêm"),
    CHUA_TIEM("Chưa tiêm"),
    DANG_CHO("Đang chờ");

    private final String label;

    VaccineStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
