package com.pet.converter;

import com.pet.config.ModelMapperConfig;
import com.pet.entity.Category;
import com.pet.entity.Promotion;
import com.pet.entity.Voucher;
import com.pet.exception.ResourceNotFoundException;
import com.pet.modal.request.PromotionRequestDTO;
import com.pet.modal.request.VoucherRequestDTO;
import com.pet.modal.response.PromotionResponseDTO;
import com.pet.modal.response.VoucherResponseDTO;
import com.pet.repository.VoucherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoucherConverter {
    @Autowired
    private ModelMapperConfig modelMapper;
    @Autowired
    private VoucherRepository voucherRepository;

    public VoucherResponseDTO mapToDTO(Voucher voucher) {
        return modelMapper.getModelMapper().map(voucher, VoucherResponseDTO.class);
    }

    public Voucher mapToEntity(VoucherRequestDTO requestDTO, Voucher voucher) {
        modelMapper.getModelMapper().map(requestDTO, voucher);

        if(voucher.getVoucherId() == null){
            voucher.setVoucherId(generateVoucherId());
        }
        return voucher;
    }

    private String generateVoucherId() {
        long count = voucherRepository.count() + 1;
        return String.format("V%03d", count);
    }
}
