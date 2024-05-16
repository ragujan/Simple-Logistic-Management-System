package com.jiat.web.servlet.merchant;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.DestinationService;
import com.jiat.ejb.remote.OrderService;
import com.jiat.ejb.remote.ProductService;
import com.jiat.ejb.remote.RetrieveDestination;
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

    @EJB
    private RetrieveDestination retrieveDestination;

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
            List<Destination> destinations = retrieveDestination.retrieveDestinations();

            // Print product names to console for testing
//            for (Product product : products) {
//                System.out.println("Product Name: " + product.getTitle());
//            }

            destinations.forEach(e-> System.out.println(e.getDestinationName()));
            request.setAttribute("productList", products);
            request.setAttribute("destinationList", destinations);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/make-order.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String qty = request.getParameter("qty");
        String product = request.getParameter("product");
        String expectedDate = request.getParameter("expectedDate");
        String destinationId = request.getParameter("destinationId");

        System.out.println("destination id "+ destinationId);
//        response.getWriter().write(destinationId);
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
            boolean status = orderService.createOrder(destinationId,merchantName, product, qty, expectedDate);
            if (status) {
                request.setAttribute("success_message", "Order is made success");
                RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("error_message", "couldn't make the order, try again maybe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
                dispatcher.forward(request, response);

            }
        }
    }

}
