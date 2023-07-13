package com.example.Servlet;

import com.example.Bean.Report;
import com.example.Dao.ReportDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * @author Cyan
 * @ date 2023/6/14
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private ReportDAO reportDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
            reportDAO = new ReportDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            try {
                Report report = reportDAO.getReportById(id);
                request.setAttribute("report", report);
                request.getRequestDispatcher("/report.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                List<Report> reports = reportDAO.getAllReports();
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/reports.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String reportName = request.getParameter("report_name");
            String reportData = request.getParameter("report_data");
            Report report = new Report();
            report.setReportName(reportName);
            report.setReportData(reportData);
            try {
                reportDAO.addReport(report);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                reportDAO.deleteReport(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath() + "/report");
    }
}
