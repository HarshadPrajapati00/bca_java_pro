import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession(false);

        if (session == null) {
            out.println("<h3>Session Expired! Please Login</h3>");
            out.println("<a href='login.html'>Login</a>");
            return;
        }

        String user = (String) session.getAttribute("username");

        // Read Cookie
        String cookieUser = "";
        Cookie[] cks = req.getCookies();
        if (cks != null) {
            for (Cookie c : cks) {
                if (c.getName().equals("user")) {
                    cookieUser = c.getValue();
                }
            }
        }

        out.println("<h2>Welcome " + user + "</h2>");
        out.println("<h4>Cookie User: " + cookieUser + "</h4>");
        out.println("<a href='logout'>Logout</a>");
    }
}
