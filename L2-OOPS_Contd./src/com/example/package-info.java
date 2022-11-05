package com.example;


/**
 *  Factory design
 *      -
 *
 *      Booking.com
 *
 *         - Searcher Payload {
 *              origin
 *              destination
 *              no of pax
 *              from date and to date
 *          }
 *
 *
 *              ---- Suppliers for different results
 *                  - GDS (Global Distribution System)   - Sabre , Galileo , amadeus.com (Searchers / Suppliers)
 *                  - {Finnair
 *                     Emirates
 *                     Pegasus
 *                  }
 *                  -- merged
 *                              ---> extract and refine price data - we can give commissions (discounts)
 *                                                              + add markup (Convinience fee)
 *                              <------------- results returned back to user
 *
 *
 *
 *                                  ComputePriceData (PriceCalculator , <ISearchResults>)
 *          HotelPriceData      ---  ComputePriceData (PriceCalculator , <ISearchResults>)
 *          AirPriceData        ---  ComputePriceData (PriceCalculator , <ISearchResults>)
 *
 *
 *
 *
 *
 *
 *      // similar inferences for multiple products
 *          - Air Booking
 *          - Hotel Booking
 *          - Insurance {health , term and trucks (vehicle) other separate product} Booking
 *          - Cab booking
 *
 *
 *
 *
 *
 *
 *
 */