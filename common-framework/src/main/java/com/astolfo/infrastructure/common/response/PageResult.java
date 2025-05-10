package com.astolfo.infrastructure.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
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


    public static <T> PageResult<T> init(IPage<T> page) {
        return PageResult
                .<T>builder()
                .records(page.getRecords())
                .pages(page.getPages())
                .total(page.getTotal())
                .current(page.getCurrent())
                .size(page.getSize())
                .build();
    }
}
