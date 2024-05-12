package com.jiat.web.servlet.merchant;

import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.OrderService;
import com.jiat.ejb.remote.ProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/make-order")
public class MakeOrderServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private OrderService orderService;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String qty = request.getParameter("qty");
        String product = request.getParameter("product");

        System.out.println("product name is " + product);

        // Dispatch to JSP page
        if (request.getSession().getAttribute("name") == null) {
            return;
        }
        String merchantName = request.getSession().getAttribute("name").toString();
        if (productService.getProductsByProductName(product) != null) {
            System.out.println("not null");
            Product productEntity = productService.getProductsByProductName(product);

            System.out.println("product weight is " + productEntity.getWeight());
            boolean status = orderService.createOrder(merchantName, product, qty);
            if(status){
                request.setAttribute("success_message", "Order is made success");
                response.sendRedirect("success_page/common_page.jsp");
            }else{

            }
        }
    }

}
