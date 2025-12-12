package servlets;

import db.DBConnection;
import model.Employee;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "view";    

        switch (action) {
            case "view":
                viewEmployees(res);
                break;
            default:
                res.getWriter().println("<h3>Invalid GET Action!</h3> " + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            res.getWriter().println("<h3>No action provided!</h3>");
            return;
        }

        switch (action) {
            case "add":
                addEmployee(req, res);
                break;

            case "update":
                updateEmployee(req, res);
                break;

            case "delete":
                deleteEmployee(req, res);
                break;

            default:
                res.getWriter().println("<h3>Invalid POST Action!</h3>");
        }
    }

    // ----------------------------------
    // ADD EMPLOYEE
    // ----------------------------------
    private void addEmployee(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();

        Employee emp = new Employee(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("email"),
                req.getParameter("phone"),
                req.getParameter("department"),
                req.getParameter("position"),
                req.getParameter("salary"),
                req.getParameter("hireDate")
        );

        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO employees (first_name, last_name, email, phone, department, position, salary, hire_date) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setString(5, emp.getDepartment());
            ps.setString(6, emp.getPosition());
            ps.setString(7, emp.getSalary());
            ps.setString(8, emp.getHireDate());

            int i = ps.executeUpdate();

            if (i > 0)
                out.println("<h3>Employee Added Successfully!</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");
            else
                out.println("<h3>Failed to Add Employee</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    // ----------------------------------
    // UPDATE EMPLOYEE
    // ----------------------------------
    private void updateEmployee(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, department=?, position=?, salary=?, hire_date=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, req.getParameter("firstName"));
            ps.setString(2, req.getParameter("lastName"));
            ps.setString(3, req.getParameter("email"));
            ps.setString(4, req.getParameter("phone"));
            ps.setString(5, req.getParameter("department"));
            ps.setString(6, req.getParameter("position"));
            ps.setString(7, req.getParameter("salary"));
            ps.setString(8, req.getParameter("hireDate"));
            ps.setInt(9, Integer.parseInt(req.getParameter("id")));

            int i = ps.executeUpdate();

            if (i > 0)
                out.println("<h3>Employee Updated Successfully!</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");
            else
                out.println("<h3>Update Failed!</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    // ----------------------------------
    // DELETE EMPLOYEE
    // ----------------------------------
    private void deleteEmployee(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(req.getParameter("id")));

            int i = ps.executeUpdate();

            if (i > 0)
                out.println("<h3>Employee Deleted Successfully!</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");
            else
                out.println("<h3>Employee Not Found!</h3>" + "/n <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    // ----------------------------------
    // VIEW EMPLOYEES
    // ----------------------------------
    private void viewEmployees(HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<h2>All Employees</h2>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr>"
                + "<th>ID</th><th>First Name</th><th>Last Name</th>"
                + "<th>Email</th><th>Phone</th><th>Dept</th>"
                + "<th>Position</th><th>Salary</th><th>Hire Date</th>"
                + "</tr>");

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");
             out.println(" <br/>" + " <a href='/pro_bca005'> Go to HOME </a> ");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("first_name") + "</td>");
                out.println("<td>" + rs.getString("last_name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("position") + "</td>");
                out.println("<td>" + rs.getString("salary") + "</td>");
                out.println("<td>" + rs.getString("hire_date") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
