package com.astolfo.domain.rbac.model.valueobject.entity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Nonnull
@AllArgsConstructor
@Data
public class Symbol {

    private String symbol;


    public static Symbol of(String symbol) {
        return new Symbol(symbol);
    }

}
