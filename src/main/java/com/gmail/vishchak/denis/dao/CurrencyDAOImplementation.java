package com.gmail.vishchak.denis.dao;

import com.gmail.vishchak.denis.entity.Currency;

import java.sql.Connection;

public class CurrencyDAOImplementation extends AbstractDAO<Currency> {
    public CurrencyDAOImplementation(Connection conn, String table) {
        super(conn, table);
    }
}
