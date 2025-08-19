package com.example.iphonedropp.dtos;

import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.SectionCategory;

public class CategoryDTO {
    private Long id;

    private SectionCategory sectionCategory;
    private String name;
    private String img;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.sectionCategory = category.getSectionCategory();
        this.name = category.getName();
        this.img = category.getImg();
    }


    public Long getId() {
        return id;
    }

    public SectionCategory getSectionCategory() {
        return sectionCategory;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }
}
