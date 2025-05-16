package com.astolfo;

import com.astolfo.infrastructure.common.util.MinioUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinioTest {

    @Resource
    private MinioUtil minioUtil;


    @Test
    public void test() {

    }

}
