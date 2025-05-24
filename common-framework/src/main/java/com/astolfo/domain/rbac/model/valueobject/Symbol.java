package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Symbol {

    private String symbol;


    public static Symbol of(String symbol) {
        if (Objects.isNull(symbol)) {
            return null;
        } else {
            return new Symbol(symbol);
        }
    }

}
