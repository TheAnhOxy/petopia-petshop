package com.pet.modal.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> content;
    private long totalElements;
    private int page;
    private int size;
}