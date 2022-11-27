package com.example.L8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L8Application {

	/**
	 *
	 * 	Minor Project
	 *
	 * 		First business of Amazon
	 * 			(online books)
	 * 					-- e commerce
	 *
	 * 	Chegg.com
	 * 			(--> )
	 *
	 * 	How do you design a low level
	 * 			-- LLD for chegg.com
	 *
	 * 	HW
	 * 		- Snake and Ladder
	 * 		- Library Management
	 * 		- Cab booking (ola / uber /lyft)
	 * 		- Hotel booking / Air book - booking.com / trivago etc.
	 *
	 *
	 * 	Chegg.com
	 * 	* 	Draw out the entities (actors) and map the association
	 * 		--> relations --> bottle necks -- scale and things --> what type of Db to use
	 * 			---> what configuration support the low level.
	 *
	 *	* focus on functionalities
	 *	*	Relationships (bottle necks)
	 *	*
	 *
	 * 	Actors / Entities for Chegg
	 * 		-	Books			 * 		-	Authors
	 * 		-	User
	 *		- 	Order
	 * 		- 	Payment
	 * 		-	Notification
	 * 	functionalities
	 * 		- purchase a book / rent a book
	 * 		- Login -
	 * 		- book search by name , by author , by isbn
	 * 		- Add to cart
	 * 		- Rating and reviews (next lecture - many to many)
	 * 		- Notifications(kafka / major project -- EMAIL notifications in Major Project)
	 *		- check history of issuance
	 *		- Admin --- scrape this.
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(L8Application.class, args);
	}

}
