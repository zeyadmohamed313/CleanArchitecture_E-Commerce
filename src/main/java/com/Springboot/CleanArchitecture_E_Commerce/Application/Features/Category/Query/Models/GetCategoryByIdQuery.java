package com.Springboot.CleanArchitecture_E_Commerce.Application.Features.Category.Query.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryByIdQuery {
    public Long getId() {
        return id;
    }

    private Long id;

    public GetCategoryByIdQuery(Long id) {
        this.id = id;
    }
}
