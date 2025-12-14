import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Invalidate session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Delete cookie
        Cookie ck = new Cookie("user", "");
        ck.setMaxAge(0);
        res.addCookie(ck);

        res.sendRedirect("login.html");
    }
}
