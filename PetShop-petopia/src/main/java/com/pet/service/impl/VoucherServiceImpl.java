package com.pet.service.impl;

import com.pet.converter.VoucherConverter;
import com.pet.entity.Promotion;
import com.pet.entity.Voucher;
import com.pet.enums.PromotionVoucherStatus;
import com.pet.modal.request.ApplyVoucherRequestDTO;
import com.pet.modal.request.VoucherRequestDTO;
import com.pet.modal.response.PageResponse;
import com.pet.modal.response.PromotionResponseDTO;
import com.pet.modal.response.VoucherResponseDTO;
import com.pet.repository.VoucherRepository;
import com.pet.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherConverter voucherConverter;

    @Override
    public VoucherResponseDTO getVoucherByCode(String voucherCode) {
        return voucherRepository.findByCode(voucherCode)
                .map(voucherConverter::mapToDTO)
                .orElse(null);
    }

    @Override
    public PageResponse<VoucherResponseDTO> getAllVouchers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Voucher> voucherPage = voucherRepository.findAll(pageable);

        List<VoucherResponseDTO> voucherDTOs = voucherPage.getContent().stream()
                .map(voucherConverter::mapToDTO)
                .toList();

        PageResponse<VoucherResponseDTO> response = new PageResponse<>();
        response.setContent(voucherDTOs);
        response.setTotalElements(voucherPage.getTotalElements());
        response.setPage(page);
        response.setSize(size);
        return response;
    }

    @Override
    @Transactional
    public VoucherResponseDTO addOrUpdateVoucher(VoucherRequestDTO request) {
        Voucher voucher;
        if (request.getVoucherId() != null) {
            voucher = voucherRepository.findById(request.getVoucherId())
                    .orElseThrow(() -> new RuntimeException("Voucher not found with id: " + request.getVoucherId()));
        } else {
            voucherRepository.findByCode(request.getCode())
                    .ifPresent(p -> { throw new RuntimeException("Voucher code already exists: " + request.getCode()); });

            voucher = new Voucher();
            voucher.setCreatedAt(LocalDateTime.now());
        }
        voucher = voucherConverter.mapToEntity(request, voucher);
        Voucher savedVoucher = voucherRepository.save(voucher);
        return voucherConverter.mapToDTO(savedVoucher);
    }

    @Override
    public VoucherResponseDTO inactiveVoucher(String voucherId) {
        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new RuntimeException("Voucher not found with id: " + voucherId));
        voucher.setStatus(PromotionVoucherStatus.INACTIVE);
        Voucher updatedVoucher = voucherRepository.save(voucher);
        return voucherConverter.mapToDTO(updatedVoucher);
    }

    @Override
    public VoucherResponseDTO applyVoucher(ApplyVoucherRequestDTO request) {
        Voucher voucher = voucherRepository.findByCode(request.getVoucherCode())
                .orElseThrow(() -> new RuntimeException("Voucher not found with code: " + request.getVoucherCode()));

        voucherConverter.validateVoucher(voucher, request.getOrderAmount());

        // Trả về thông tin voucher mà không áp dụng thực sự (chưa tăng usedCount)
        return voucherConverter.mapToDTO(voucher);
    }

    // Xác nhận áp dụng voucher sau khi thanh toán đơn hàng thành công
    @Transactional
    public VoucherResponseDTO confirmVoucherApplication(String voucherCode, String orderId) {
        Voucher voucher = voucherRepository.findByCode(voucherCode)
                .orElseThrow(() -> new RuntimeException("Voucher not found with code: " + voucherCode));

        // Áp dụng thực sự (tăng usedCount, set trạng thái nếu cần)
        voucher.setUsedCount(voucher.getUsedCount() + 1);

        if(voucher.getMaxUsage() != null && voucher.getUsedCount() >= voucher.getMaxUsage()) {
            voucher.setStatus(PromotionVoucherStatus.INACTIVE);
        }
        Voucher savedVoucher = voucherRepository.save(voucher);

        return voucherConverter.mapToDTO(savedVoucher);
    }
}
