import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Simple validation
        if (user.equals("admin") && pass.equals("123")) {

            // Create Session
            HttpSession session = req.getSession();
            session.setAttribute("username", user);

            // Create Cookie
            Cookie ck = new Cookie("user", user);
            ck.setMaxAge(60 * 60); // 1 hour
            res.addCookie(ck);

            res.sendRedirect("dashboard");

        } else {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<h3>Invalid Username or Password</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}
