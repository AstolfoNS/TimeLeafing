package com.astolfo.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PageResult<T> {

    private List<T> records;

    private Long total;

    private Long pages;

    private Long current;

    private Long size;

}
