package com.jiat.web.servlet.merchant;

import com.jiat.core.models.MerchantOrderDataModel;
import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.entity.Product;
import com.jiat.ejb.remote.OrderInsertionService;
import com.jiat.ejb.remote.ProductService;
import com.jiat.ejb.remote.RetrieveDestination;
import com.jiat.ejb.remote.RetrieveMerchantOrders;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/make-order")
public class MakeOrderServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private OrderInsertionService orderInsertionService;

    @EJB
    private RetrieveDestination retrieveDestination;
    @EJB
    private RetrieveMerchantOrders retrieveMerchantOrdersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatch to JSP page
        if (request.getSession().getAttribute("name") == null) {
            return;
        }
        String merchantName = request.getSession().getAttribute("name").toString();
        List<Orders> merchantOrders = retrieveMerchantOrdersBean.getOrders(merchantName);
        List<MerchantOrderDataModel> merchantOrderDataModels = new ArrayList<>();
        merchantOrders.stream().forEach(e->{
            MerchantOrderDataModel model = new MerchantOrderDataModel();
            model.setId(e.getId());
            model.setDestination(e.getDestination().getDestinationName());
            model.setExpectedDate(e.getExpectedDate());
            model.setCreatedAt(e.getCreatedAt());
            model.setQty(Integer.toString(e.getQty()));
            model.setProduct(e.getProductId().getTitle());
            model.setOrderStatus(e.getOrderStatus());
            merchantOrderDataModels.add(model);

        });
        if (productService.getProductsByMerchantName(merchantName) != null) {
            System.out.println("not null");
            List<Product> products = productService.getProductsByMerchantName(merchantName);
            List<Destination> destinations = retrieveDestination.retrieveDestinations();



            destinations.forEach(e-> System.out.println(e.getDestinationName()));
            request.setAttribute("productList", products);
            request.setAttribute("destinationList", destinations);
            request.setAttribute("orderList",merchantOrderDataModels);
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


        // Dispatch to JSP page
        if (request.getSession().getAttribute("name") == null) {
            return;
        }
        String merchantName = request.getSession().getAttribute("name").toString();
        if (productService.getProductsByProductName(product) != null) {
            Product productEntity = productService.getProductsByProductName(product);

            boolean status = orderInsertionService.createOrder(destinationId,merchantName, product, qty, expectedDate);
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
