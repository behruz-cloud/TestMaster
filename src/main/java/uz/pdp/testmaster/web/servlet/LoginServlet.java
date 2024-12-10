package uz.pdp.testmaster.web.servlet;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uz.pdp.testmaster.web.util.MyListener.EMF;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gmail = req.getParameter("gmail");
        String password = req.getParameter("password");
        EntityManager em = EMF.createEntityManager();
        if (gmail.equals("1") && password.equals("1")) {
            resp.sendRedirect("/sendEmail.jsp");
        }else {
            resp.sendRedirect("/loginPage.jsp");
        }
    }
}
