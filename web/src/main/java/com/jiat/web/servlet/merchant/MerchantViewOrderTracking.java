package com.jiat.web.servlet.merchant;

import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.remote.FreightRetrieval;
import com.jiat.ejb.remote.FreightTrackingDataGeneration;
import com.jiat.ejb.remote.FreightTrackingForMerchant;
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

@WebServlet("/merchant-view-order-tracking")
public class MerchantViewOrderTracking extends HttpServlet {


    @EJB
    FreightTrackingForMerchant freightTrackingForMerchant;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("name") == null) {
            return;
        }
        String merchantName = request.getSession().getAttribute("name").toString();

        List<FreightTrackingDataModel> trackingDataModels = freightTrackingForMerchant.getFreightTrackingByMerchantOrder(merchantName);

        request.setAttribute("trackingDataModels",trackingDataModels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/merchant/merchant-view-order-tracking.jsp");
        dispatcher.forward(request, response);


    }
}
