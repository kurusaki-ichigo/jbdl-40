package com.example.L8.service;

import com.example.L8.entities.Books;
import com.example.L8.entities.Order;
import com.example.L8.entities.OrderStatus;
import com.example.L8.entities.User;
import com.example.L8.exception.BookNotAvailableException;
import com.example.L8.exception.UserNotFoundException;
import com.example.L8.models.StatusCodes;
import com.example.L8.repository.OrderRepository;
import com.example.L8.requests.CreateOrdersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {



    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    /**
     *  what would be the flow in layman terms when an order is placed
     *
     *      check if all book is present or not..
     *              --> then what ?
     *              Create an order in pending state
     *                      --> mark all the books as not available / booked
     *                                          --------------------------------> Take payment from a user
     *                                                                      (Payment)
     *                  --> mark the order success
     *                  done
     *
     *
     *
     *
     */

    @Transactional
    public Order createOrderV2(CreateOrdersRequest request){
        Optional<User> user = userService.findById(request.getUserId());
        if(user.isEmpty()){
            throw  new UserNotFoundException(StatusCodes.USER_NOT_FOUND);
        }

        List<Books> allBooks = bookService.findAllByIds(request.getBookIds());
        /**
         * available or not
         */
        Optional<Books> any = allBooks.stream().filter(books -> !books.isAvailable()).findAny();
        if(any.isPresent()){
            throw new BookNotAvailableException(StatusCodes.BOOK_UNAVAILABLE);
        }

        Double cartAmount = allBooks.stream().map(Books::getPurchaseAmount).reduce(0d, Double::sum);
        Order order = Order.builder()
                .booksList(allBooks)
                .orderStatus(OrderStatus.PENDING)
                .user(user.get())
                .cartAmount(cartAmount).build();
        saveOrUpdate(order);
        bookService.purchaseBooks(allBooks);
        order.setOrderStatus(OrderStatus.SUCCESS);

        return saveOrUpdate(order);
    }



    public void createOrder(CreateOrdersRequest request){
        List<Books> allBooks = bookService.findAllByIds(request.getBookIds());
        /**
         * available or not
         */
        Optional<Books> any = allBooks.stream().filter(books -> !books.isAvailable()).findAny();
        if(any.isPresent()){
            throw new BookNotAvailableException(StatusCodes.BOOK_UNAVAILABLE);
        }

        Double cartAmount = allBooks.stream().map(Books::getPurchaseAmount).reduce(0d, Double::sum);
        Order order = Order.builder()
                .booksList(allBooks)
                .orderStatus(OrderStatus.PENDING)
                .cartAmount(cartAmount).build();
        saveOrUpdate(order);
        try {

            bookService.purchaseBooks(allBooks);
            order.setOrderStatus(OrderStatus.SUCCESS);
            saveOrUpdate(order);
        } catch (Exception exception){
            order.setOrderStatus(OrderStatus.INVALID);
            saveOrUpdate(order);
            bookService.refundBooks(allBooks);
        }

    }

    public Order saveOrUpdate(Order order){
        return orderRepository.save(order);
    }


}
