package com.oracleous.extention_manager.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Gender {
    MALE, FEMALE
}
