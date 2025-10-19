package com.pet.modal.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplyVoucherRequestDTO {
    @NotBlank(message = "voucherCode cannot be null or empty")
    private String voucherCode;
    @NotNull(message = "orderAmount cannot be null")
    private Double orderAmount;

    public void validate(){
        if(voucherCode == null || voucherCode.isEmpty()){
            throw new IllegalArgumentException("voucherCode cannot be null or empty");
        }
        if(orderAmount == null || orderAmount <= 0){
            throw new IllegalArgumentException("orderAmount must be greater than 0");
        }
    }
}
