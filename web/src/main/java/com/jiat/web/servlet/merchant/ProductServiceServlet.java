package com.jiat.web.servlet.merchant;

import com.jiat.ejb.remote.ProductService;
import com.jiat.ejb.remote.RegisterMerchant;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register-product")
public class ProductServiceServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            Float weight = Float.parseFloat(request.getParameter("weight"));
            String units = request.getParameter("units");
            boolean success = productService.registerProduct(title, weight, units);

            if (success) {
                response.sendRedirect("success_page/product_success.jsp");
            } else {
                response.sendRedirect("errors/product_error.jsp");
            }
        }catch (EJBAccessException e){
            response.sendError(403);
        }
    }
}
