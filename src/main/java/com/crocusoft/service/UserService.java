package com.crocusoft.service;

import com.crocusoft.driver.DatabaseDriver;
import com.crocusoft.driver.Encrypter;
import com.crocusoft.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService extends GenericService{
    private static final String GET_USER_BY_USERNAME_AND_PASSWORD
            = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String ADD_USER = "INSERT INTO users " +
            "(username, email, password, name, surname, phone_number, role, is_enable) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public UserService() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    public User getUserByUsernameAndPassword(String username, String password)
            throws SQLException, NoSuchAlgorithmException {
        List<String> parameters = new ArrayList<String>();
        User user = null;
        parameters.add(username);
        parameters.add(Encrypter.getEncrypted(password));

        ResultSet resultSet = selectFromTable(GET_USER_BY_USERNAME_AND_PASSWORD, parameters);

        if(resultSet.next()){
            user = new User(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setEnabled(resultSet.getBoolean("is_enable"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setRole(resultSet.getString("role"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
        }

        return user;
    }

    public boolean addUser() throws NoSuchAlgorithmException, SQLException {
        return false;
    }
}
