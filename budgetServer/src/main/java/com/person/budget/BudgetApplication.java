package com.person.budget;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BudgetApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BudgetApplication.class, args);
	}

}
