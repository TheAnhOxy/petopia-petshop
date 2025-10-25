package com.pet.controller;

import com.pet.modal.request.ApplyVoucherRequestDTO;
import com.pet.modal.request.PromotionRequestDTO;
import com.pet.modal.request.VoucherRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PromotionResponseDTO;
import com.pet.modal.response.VoucherResponseDTO;
import com.pet.service.PromotionService;
import com.pet.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/{voucherCode}")
    public ResponseEntity<VoucherResponseDTO> getVoucher(@PathVariable String voucherCode){
        VoucherResponseDTO voucher = voucherService.getVoucherByCode(voucherCode);
        if (voucher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(voucher);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<VoucherResponseDTO>> getAllVouchers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(voucherService.getAllVouchers(page, size));
    }

    @PostMapping
    public ResponseEntity<VoucherResponseDTO> addOrUpdateVoucher(@RequestBody VoucherRequestDTO request){
        request.validate();
        VoucherResponseDTO voucher = voucherService.addOrUpdateVoucher(request);
        if (voucher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(voucher);
    }

    @PutMapping("/inactive/{voucherId}")
    public ResponseEntity<VoucherResponseDTO> inactiveVoucher(@PathVariable String voucherId){
        VoucherResponseDTO voucher = voucherService.inactiveVoucher(voucherId);
        if (voucher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(voucher);
    }

    @PostMapping("/apply")
    public ResponseEntity<VoucherResponseDTO> applyVoucher(@RequestBody ApplyVoucherRequestDTO request){
        request.validate();
        VoucherResponseDTO voucher = voucherService.applyVoucher(request);
        if (voucher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(voucher);
    }

}
