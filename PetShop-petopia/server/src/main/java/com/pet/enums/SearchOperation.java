package com.pet.enums;

import lombok.ToString;

@ToString
public enum SearchOperation {
    EQUAL,
    LIKE,
    GREATER_THAN_OR_EQUAL,
    LESS_THAN_OR_EQUAL,
    IN
}