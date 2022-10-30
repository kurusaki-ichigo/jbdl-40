package com.company;

import com.company.entity.UserInfo;

import java.time.LocalDate;
import java.util.Random;

public class Main {

    /**
     *
     *
     * 1) Access Modifier
     *
     *      Default access modifier vs default keyword -- later end
     *
     *            FE   ---- (Application) ------ DATASOURCE -- (S3, Mysql (datatype blob))
     *
     *
     *
     *
     *
     *      File Entity {
     *
     *          id = autoincrement by datasource (mysql) - / (s3 -- name should be unqiue enough/ path)
     *          byte[]  data;
     *          name "random_1212934u2947129"
     *          extention - ".xls"
     *
     *          createdBy - userId
     *          updatedBy - userId;
     *          createdAt -
     *          updatedAt -
     *      }
     *
     *          Hibernate - ORM - spring data jpa
     *
     *       {
     *          Hibernate L2 Caching
     *          Hibernate L1 Caching
     *       }
     *          CRUD (always)
     *          - Create (excel which would be uploaded) ------> (new )
     *          - Read - read it by name / read it by person uploaded / read it by timestamp            -- datasource
     *          - update - get the file with the same id  - and then perform operation                   -- happens over id
     *          - Delete - delete the file with same id
     *
     * @param args
     */

//    @Secured  -- it was an old standard
//    @PreAuthrize() -- its a new standard - it has more flexibility
//
//    @GetMapping
//


    public static void main(String[] args) {
	// write your code here

        // receive a request
        // to create a user
        UserInfo userInfo = new UserInfo();
        userInfo.setId((long) Math.round(6L));
        userInfo.setName("name");
        userInfo.dob = LocalDate.now();
        userInfo.setEmail("random@exmaple");



        userInfo.add();

    }
}
