package com.sample.org.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		/**
		 *
		 * a)  	Application started
		 * 		Hey....
		 * 	server running
		 * b ) Hey....
		 * server running
		 * c) 	Application started
		 * Hey...
		 * Application Exited ..
		 * server stopped
		 *
		 * O/P
		 * 		-	A
		 *
		 * 		-	C
		 * 		-	B
		 *
		 */
		SpringApplication.run(NotificationServiceApplication.class, args);
		System.out.println("Hey !... I was doing Just fine before I met you , drink too much");
	}

	/**
	 * inbuilt H2 and mysql (tomorrow)
	 *
	 *
	 *	tomcat
	 *what a server --> 24 x 7
	 * 	process exited
	 *	-- play -->
	 *
	 *
	 * 	providing embedded  ----> embedded ?
	 */

}
