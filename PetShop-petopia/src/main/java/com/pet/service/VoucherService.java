package com.pet.service;

import com.pet.modal.request.VoucherRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.VoucherResponseDTO;

public interface VoucherService {
    VoucherResponseDTO getVoucherByCode(String voucherCode);
    PageResponse<VoucherResponseDTO> getAllVouchers(int page, int size);
    VoucherResponseDTO addOrUpdateVoucher(VoucherRequestDTO request);
}
