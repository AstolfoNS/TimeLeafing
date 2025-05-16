package com.astolfo.domain.domain.rbac.model.valueobject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Symbol {

    private String symbol;


    public static Symbol of(String symbol) {
        return new Symbol(symbol);
    }

}
