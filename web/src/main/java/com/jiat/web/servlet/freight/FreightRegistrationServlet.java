package com.jiat.web.servlet.freight;

import com.jiat.core.models.FreightDataModel;
import com.jiat.ejb.entity.Freight;
import com.jiat.ejb.entity.Route;
import com.jiat.ejb.entity.Transportation;
import com.jiat.ejb.remote.FreightRegisterService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DateFormatterUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/freight-registration")
public class FreightRegistrationServlet extends HttpServlet {

    @EJB
    private FreightRegisterService freightRegisterService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transportation> transportationList = freightRegisterService.getAllTransportations();
        List<Route> routeList = freightRegisterService.getAllRoutes();
        List<Freight> freightList = freightRegisterService.getAllFreights();
        List<FreightDataModel> frightDataModelList = new ArrayList<>();


        freightList.forEach(e-> {
            FreightDataModel freightDataModel = new FreightDataModel();
            freightDataModel.setRouteId(e.getRoute().getId());
            freightDataModel.setRouteName(e.getRoute().getName());
            freightDataModel.setTransportationId(e.getTransportation().getId());
            freightDataModel.setTransportationName(e.getTransportation().getName());
            freightDataModel.setRouteId(e.getRoute().getId());
            freightDataModel.setWeight(Integer.toString(e.getWeight()));
            freightDataModel.setEta(e.getEta());
            freightDataModel.setStartDate(e.getStartDate());
            freightDataModel.setEndDate(e.getEndDate());
            freightDataModel.setId(e.getId());

            if(e.isFailed()){
                freightDataModel.setFailed("true");
            }else{
                freightDataModel.setFailed("false");
            }

            if(e.isDelivered()){
                freightDataModel.setDelivered("true");
            }else{
                freightDataModel.setDelivered("false");
            }

            if(e.isHasStarted()){
                freightDataModel.setHasJourneyStarted("true");
            }else{
                freightDataModel.setHasJourneyStarted("false");
            }

            frightDataModelList.add(freightDataModel);
        });

        request.setAttribute("freightList", frightDataModelList);
        request.setAttribute("transportationList", transportationList);
        request.setAttribute("routeList", routeList);

        request.getRequestDispatcher("/admin/freight/freight-registration.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transportationId = request.getParameter("transportation");
        String routeId = request.getParameter("route");
        String weight = request.getParameter("weight");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String eta = request.getParameter("eta");

        System.out.println("Transportation ID: " + transportationId);
        System.out.println("Route ID: " + routeId);
        System.out.println("Weight: " + weight);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("ETA: " + eta);

        LocalDateTime formattedStartDate = DateFormatterUtil.format(startDate);
        LocalDateTime formattedEndDate = DateFormatterUtil.format(endDate);

        FreightDataModel freightDataModel = new FreightDataModel();
        freightDataModel.setEta(Double.parseDouble(eta));
        freightDataModel.setStartDate(formattedStartDate);
        freightDataModel.setEndDate(formattedEndDate);
        freightDataModel.setTransportationId(Integer.parseInt(transportationId));
        freightDataModel.setWeight(weight);
        freightDataModel.setRouteId(Integer.parseInt(routeId));
        boolean insertionSuccess = freightRegisterService.addFreight(freightDataModel);

        if(insertionSuccess){
            request.setAttribute("success_message", "Freight successfully registered");
            RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error_message", "Failed to add Freight. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
            dispatcher.forward(request, response);
        }


    }


}
