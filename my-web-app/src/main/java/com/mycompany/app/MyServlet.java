package com.mycompany.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type to text/html
        response.setContentType("text/html");

        // Get the file as an InputStream
        try (InputStream inputStream = getServletContext().getResourceAsStream("/index.html");
             OutputStream outputStream = response.getOutputStream()) {

            if (inputStream == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
                return;
            }

            // Copy the content from the InputStream to the OutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

