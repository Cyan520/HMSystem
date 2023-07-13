package com.example.Servlet;

import com.example.Bean.EventLog;
import com.example.Bean.Vacation;
import com.example.Dao.EventLogDAO;
import com.example.Dao.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/21
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final LoginDAO loginDAO = new LoginDAO();
    private EventLogDAO eventLogDAO = new EventLogDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60*24*7);
        session.setAttribute("username", username);
        if(loginDAO.haveUser(username, password)){
            EventLog eventLog = new EventLog();
            eventLog.setUsername(username);
            eventLog.setEventTime(new Date());
            eventLog.setEventName("登陆");
            eventLog.setEventDetail("登陆成功");
            try {
                eventLogDAO.addEventLog(eventLog);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("homePage.jsp");
        }else{
            request.setAttribute("loginError", "你输入的用户信息有误！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }
}
