package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    ArrayList<String> f = new ArrayList<>();
    int i = 2;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        f.add(username);
        f.add(password);
        response.sendRedirect("registration");
        System.out.println(f.toString());
        FileWriter writer = new FileWriter("C:/Users/serge/OneDrive/Документы/GitHub/RegistrationCSV/src/main/java/org/example/usernames_passwords.csv");
        for (String data: f){
            if (i % 2 == 0){
                writer.append(data + ",");
            }
            else {
                writer.append(data + "\n");
            }
            i++;
        }
        writer.flush();
        writer.close();
    }
}