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

    //  User
    public  static  final  String USER_BASE = BASE_API + "/users";
    public  static  final String REGISTER = USER_BASE+"/register";
    public  static  final String LOGIN = USER_BASE+"/login";

    // Cart Routes
    public static final String CART_BASE = BASE_API + "/cart";
    public static final String CART_ADD =  "/add";
    public static final String CART_REMOVE = "/remove";
    public static final String CART_GET =  "/{userId}";
}
