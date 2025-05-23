package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Symbol {

    private String symbol;


    public static boolean isValid(String symbol) {
        // TODO: valid

        return true;
    }

    public static Symbol of(String symbol) {
        if (isValid(symbol)) {
            return new Symbol(symbol);
        } else {
            throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }

    }

}
