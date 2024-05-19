package com.jiat.web.servlet.admin.freight;

import com.jiat.core.models.FreightDataModel;
import com.jiat.core.models.FreightTrackingDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.impl.freight_tracking.FreightTrackingDataGenerationImpl;
import com.jiat.ejb.remote.FreightRetrieval;
import com.jiat.ejb.remote.FreightTrackingDataGeneration;
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

@WebServlet("/freight-tracking")
public class FreightTrackingServlet extends HttpServlet {


    @EJB
    FreightTrackingDataGeneration freightTrackingDataGeneration;

    @EJB
    FreightRetrieval freightRetrieval;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Freight> freightList = freightRetrieval.getAllFreights();
        List<FreightTrackingDataModel> trackingDataModels = new ArrayList<>();
        for (Freight freight: freightList
             ) {
            if(freightTrackingDataGeneration.getFreightTracking(freight) !=null){
              FreightTrackingDataModel model = freightTrackingDataGeneration.getFreightTracking(freight);
                System.out.println("model "+model.getFreightProgress());
                trackingDataModels.add(model);

            }
        }
        request.setAttribute("trackingDataModels",trackingDataModels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/freight/freight-tracking.jsp");
        dispatcher.forward(request, response);


    }
}
