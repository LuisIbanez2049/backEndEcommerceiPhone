package com.example.iphonedropp.dtos.records;

import java.util.List;

public record RecordCreateProduct(String name, int cant, Long categoryId, List<String> imageLinks) {
}
