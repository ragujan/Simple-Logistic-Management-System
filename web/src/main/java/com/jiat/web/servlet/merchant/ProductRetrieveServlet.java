package com.jiat.web.servlet.merchant;

import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/make-order")
public class ProductRetrieveServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        if (request.getSession().getAttribute("name") == null) {
            return;
        }
        String merchantName = request.getSession().getAttribute("name").toString();
        if (productService.getProductsByMerchantName(merchantName) != null) {
            System.out.println("not null");
            List<Product> products = productService.getProductsByMerchantName(merchantName);

            // Print product names to console for testing
            for (Product product : products) {
                System.out.println("Product Name: " + product.getTitle());
            }

            request.setAttribute("productList", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/make-order.jsp");
            dispatcher.forward(request, response);
        }
    }


}
