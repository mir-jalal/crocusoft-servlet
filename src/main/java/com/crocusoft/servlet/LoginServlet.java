package com.crocusoft.servlet;

import com.crocusoft.model.User;
import com.crocusoft.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
        } catch (SQLException | IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new ServletException("Can't get Service");
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html");

        try {
            user = userService.getUserByUsernameAndPassword(username, password);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            resp.setStatus(500);
            doGet(req,resp);
            return;
        }

        if(user == null){
            resp.setStatus(403);
            resp.setStatus(500);
            doGet(req,resp);
            return;
        }else if(user.getRole().equals("admin")){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
    }
}
