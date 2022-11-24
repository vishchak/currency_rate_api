package com.gmail.vishchak.denis;

import com.gmail.vishchak.denis.dao.CurrencyDAOImplementation;
import com.gmail.vishchak.denis.datasource.ConversionRate;
import com.gmail.vishchak.denis.entity.Currency;
import com.gmail.vishchak.denis.utils.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Currency");
                }
                Scanner sc = new Scanner(System.in);
                CurrencyDAOImplementation dao = new CurrencyDAOImplementation(conn, "Currency");
                dao.createTable(Currency.class);

                while (true) {
                    System.out.println("1: add currency");
                    System.out.println("2: view rates");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            System.out.print("Enter base currency: ");
                            String baseCurrency = sc.nextLine();
                            System.out.print("Enter conversion currencies separated by commas: ");
                            String conversionCurrencies = sc.nextLine();

                            for (Currency cur :
                                    (ConversionRate.conversionRateToday(baseCurrency, conversionCurrencies.split(",")))) {
                                dao.add(cur);
                            }
                            break;
                        case "2":
                            List<Currency> list = dao.getAll(Currency.class);
                            for (Currency currency :
                                    list) {
                                System.out.println(currency);
                            }
                            break;
                        default:
                            return;
                    }
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}