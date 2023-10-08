package org.example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {

    protected boolean correctData(String name, String pass) throws IOException {
        BufferedReader Reader = new BufferedReader(new FileReader("C:/Users/serge/OneDrive/Документы/GitHub/RegistrationCSV/src/main/java/org/example/usernames_passwords.csv"));
        String line;
        while ((line = Reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(name) && data[1].equals(pass)){
                return true;
            }
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signIn.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(correctData(username,password)) {
            request.getSession(true).setAttribute("User", username);
            response.sendRedirect("profile");
        } else {
            response.sendRedirect("registration");
        }
    }
}
