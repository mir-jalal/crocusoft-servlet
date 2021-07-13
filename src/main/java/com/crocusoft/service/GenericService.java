package com.crocusoft.service;

import com.crocusoft.driver.DatabaseDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericService {
    Connection connection;
    GenericService() throws SQLException, IOException, ClassNotFoundException {
        connection = DatabaseDriver.getInstanceOfConnection();
    }

    public ResultSet selectFromTable(String query, List<String> parameters) throws SQLException {
        int count = 0;
        PreparedStatement selectFromTableStatement = connection.prepareStatement(query);

        for(String parameter : parameters){
            selectFromTableStatement.setString(++count, parameter);
        }

        return selectFromTableStatement.executeQuery();
    }
}
