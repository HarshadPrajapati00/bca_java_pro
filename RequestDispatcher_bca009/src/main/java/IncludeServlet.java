import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/include")
public class IncludeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // include header
        RequestDispatcher rd1 = request.getRequestDispatcher("header.html");
        rd1.include(request, response);

        out.println("<p>Main content from IncludeServlet</p>");

        // include footer
        RequestDispatcher rd2 = request.getRequestDispatcher("footer.html");
        rd2.include(request, response);
    }
}
