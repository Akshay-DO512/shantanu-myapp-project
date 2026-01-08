
package com.example.webapp.controller;

import com.example.webapp.dao.UserDao;
import com.example.webapp.model.User;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.Properties;

public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = new User(req.getParameter("name"), req.getParameter("email"));
        UserDao.save(user, props);
        resp.sendRedirect("index.jsp");
    }
}
