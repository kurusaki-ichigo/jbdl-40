package com.example.security;


/**
 *
 *      Lets debrief
 *          --> Authentication
 *          A user is valid or not
 *                      401 error
 *
 *          --> Authorization
 *         A user is allowed to access a resource or not
 *                      403 Forbidden
 *
 *
 *
 *     Food Aggregators / Cab
 *
 *     Entites with respect to food aggregators like
 *     Zomato , Swiggy , Bolt etc.
 *
 *     User Entity (abstraction)
 *
 *     Where as
 *     Concrete implementations
 *     Customer
 *     Merchant -- Restaurant Manager
 *     Delivery partner - Driver
 *
 *
 *
 *
 *      POST ----> /order
 *      Customer invoked by customer
 *      Merchant (not access it)
 *      Delivery Partner -- (no)
 *
 *
 * @Secured("[ROLE_MERCHANT, ROLE_CUSTOMER , ROLE_DELIVERY_PARTNER]")
 * @PreAuthorize('hasRole("MERCHANT") OR hashRole("CUSTOMER") OR hasRole("DELIVERY_PARTNER")')
 *      --> view order details
 *     GET  /order
 *      Customer (yes)
 *      Merchant (Yes)
 *      Delivery Partner -- (Yes)
 *
 *
 *
 * @Secured("ROLE_MERCHANT")
 *
 * SPEL = --
 * @PreAuthorize('hasRole("MERCHANT")')
 *      --> PATCH/POST -- /update/order
 *      Customer (no)
 *  *      Merchant (yes)
 *      Delivery partner (no)
 *
 *
 *
 *
 *
 *
 */