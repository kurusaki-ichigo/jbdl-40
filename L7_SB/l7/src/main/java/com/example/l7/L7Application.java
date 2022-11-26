package com.example.l7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L7Application {
	/**
	 *  UberEats + Paytm Free food
	 *  	-- DIstributed lock -- version around api
	 *  	--- (Versioning of api)
	 *
	 *		UberEats 				Insufficient balance 		---- Paytm as wallet
	 *			HTTP Status =  200 ok
	 *			v1/ pay
	 *			{
	 *				"error_code" : 001
	 *				--as response -- they are working fine
	 *			}
	 *
	 *			v2/pay
	 *			{
	 *				"error_code" : 001 and 002
	 *
	 *			}
	 *
	 *
	 *
	 *
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
		SpringApplication.run(L7Application.class, args);
	}

}
