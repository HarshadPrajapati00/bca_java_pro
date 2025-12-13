import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("username");
        String email = request.getParameter("email"); 
        
        if(email == ""){
         out.println("<h2> Your Session Values not available Please Set your Sesion</h2>");
          out.println("<a href='" + request.getContextPath() +
            "/second?username=" + name + "'> Go Back </a>");

           return;
        }
         
        out.println("<h2>Session Details</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Email: " + email + "<br>");
    }
}
