package com.example.ewallet.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	/**
	 *
	 * 1) Overall feature require ?
	 * 	Onboard a user
	 * 			If a  user enters a promo code (Keep it static)
	 * 			---> TOP UP a USER BALANCE (10)
	 *
	 *
	 *
	 * 	--> user
	 * 				 * 						---> Notify user
	 * 		(NEW_YEAR)
	 * 		----> Creation of new wallet
	 * 					---> Transaction Service (PENDING STATE)
	 * 											-------------------> TOP UP BY DESIRED AMOUNT ---->
	 * 										(DECREATE WALLET A by certain amount --> EWALLET (SYSTEM USER) - 10
	 * 										INCREASE wallet b by same amount + 10
	 * 					--> Transaction SUCCESS
	 * 									---> Notify user
	 * 				(Two users A or B)
	 * 				-----> DUMP Data in statement
	 * 							(Statement call) -->upload record in s3
	 * 									\----> cron to download data from s3 ---> create PDF --> upload pdf to
	 * 								public bucket ---> user can download from that bucket.
	 * 							(Security issue)
	 * 								---> since bucket is public user other user may download statements.
	 * 								--> so make the path unique and encrypt the pdf with some password
	 *
	 *
	 * 	IDEALLY any transaction which is success should trigger notification
	 * 							-----------------------------------------------> trigger notification
	 * 	or
	 * 	Any wallet change should trigger notification
	 *
	 *
	 *
	 *
	 *
	 * 	 * 			---> send an email to the user 	(your account has been successfully created)
	 *
	 * @param args
	 *
	 *
	 * 	What is the flaw in this ?
	 *
	 * 		A)
	 *
	 * 		FE ----> CREATE A USER
	 * 			<----- USERID
	 *
	 * 		FE ---> WALLET SERVICE
	 * 			<---- WALLET ID
	 *
	 * 		FE ----> TRANSACTION
	 * 			<----- TRANSACTION ID
	 *
	 * 	FE ----> WALLET ID
	 * 			-----> TOPUP WALLLET
	 * 		<------ SUCCESS
	 *
	 *
	 * 	FE ----> TRANSACTION_SERVICE (MARK TRANSACTION SUCCESS)
	 * 	FE ----> NOTIFICATION ---> TRIGGER NOTIFICATIOn
	 *
	 *
	 *
	 * 		Another flow --> B)
	 * 		 * 		Whats the flaw
	 *
	 * 						( (Bottle neck))
	 * FE	(LB)	--->	USER ---------> WALLET (CREATE) -------> TRANSACTION (PENDING)
	 * 												<----------
	 * 									TOPUP WALLET
	 * 											----------------> TRANSACTION (SUCCESS)
	 * 											<--------------------
	 * 			<---------------
	 * 			WalletId
	 *
	 * 			COSTING	-- low
	 * 	---> Bottle neck is the individual service ---> so no more extra resources of aggregator service
	 *
	 * 	Would then display on screen congrats your wallet is created
	 * 	<------
	 *
	 * 	Flow (C)
	 *
	 * 		Netflix OSS
	 * 								/--------- (USer Service Create User)
	 * 								/
	 * (ribbon)	--> Zull (Aggregator service)	--- (Bottle neck)
	 * 							|	\
	 * 							|	\----------(Wallet / create)
	 * 							|
	 * 							\-------------- Transaction (PENDING)
	 *
	 *
	 * 			COSTING	-- high
	 * 			Bottle neck is Zuul (more instance instances required if there exists any spike in the networks)
	 * 						---> not only if the spike is with respect to user
	 * 					---> more instance would be required even if spike from different microservice
	 *
	 * 			Better resillency is provided by flow C	as compared to B
	 *
	 *
	 * 	What is the circuit breaker --> ?
	 * 				Hysterix (interms of Netflix OSS)
	 * 				Resillence4j -- another spring dependency
	 *
	 * 				A ----> B ---(takes a very long time)-------> C
	 *
	 *
	 *
	 *
	 *
	 * Flow D) Event based
	 *
	 * 		B vs D  and why ??
	 *
	 * 	-- D preffered
	 *
	 *
	 * 	Service USER
	 * 				---> created User
	 * 			----> Notify ALL -----------USER-CREATED------> BROKER
	 *			<-------- NOTIFY USER has been created
	 *																			Service Wallet
	 *																<---------------CONSUMER
	 *																		(CREATE WALLET)
	 *																<------------------PUBLISH
	 *
	 *
	 *																								SERVICE TRANSACTION
	 *																<------------------------------- CONSUMER
	 *																				(CREATE TRANSACTION IN PENDING)
	 *																<----------------------------- PUBLISH
	 *
	 *																			Service Wallet
	 *																<--------------- CONSUMER
	 *																			(TOPUP WALLET)
	 *																<---------------- PUBLISH ON WALLET SUCCESS
	 *
	 *
	 *
	 *																								SERVICE TRANSACTION
	 *																<-------------------------------- CONSUMER
	 *																						(MARK TRANSACTION SUCCESS)
	 *																<--------------------------------PUBLISH USER
	 *
	 *
	 *
	 *
	 *
	 *
	 *		Flow final
	 *					(I wont be implementing)
	 *				CDC --> Change Data Capture
	 *
	 *		Whenever there is transaction that is being committed
	 *			---> its added into the bin logs as well
	 *
	 *
	 *MySql 					-------> bin logs
	 * Postgress				-------> wall logs
	 * Mongo 					------> op logs
	 *
	 *
	 * 	Service A
	 * 			---> create new user ----------> persist new user ------------_>   _____
	 *																			 (______)
	 * 																			|		|
	 * 																			|_______|
	 *
	 * 																						\
	 * 																						\
	 * 																						 \
	 * 																						 bin log
	 *																				* (maxwell / debezium)--> convert Db change
	 *																			to Kafka Events
	 *
	 *
	 *
	 */

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
