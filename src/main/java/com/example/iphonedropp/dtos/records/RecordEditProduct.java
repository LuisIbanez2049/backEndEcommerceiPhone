package com.example.iphonedropp.dtos.records;

import java.util.List;

public record RecordEditProduct(Long productId, String name,double price, int stock, Long categoryId, List<String> fileLinks, String description) {
}
