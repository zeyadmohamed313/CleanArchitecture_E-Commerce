package com.Springboot.CleanArchitecture_E_Commerce.Presentation.Router;


public class ApiRoutes {
    public static final String BASE_API = "/api";

    // ✅ Product Routes
    public static final String PRODUCT_BASE = BASE_API + "/products";
    public static final String PRODUCT_BY_ID = "/{id}";

    // ✅ Category Routes
    public static final String CATEGORY_BASE = BASE_API + "/categories";
    public static final String CATEGORY_BY_ID = "/{id}";

    // Category-Product Management
    public static final String CATEGORY_ADD_PRODUCT =  "/{categoryId}/products/{productId}";
}
