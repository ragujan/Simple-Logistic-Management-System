package com.jiat.web.servlet.admin.route;

import com.jiat.ejb.entity.Destination;
import com.jiat.ejb.remote.RouteService;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-route")
public class AddRouteServlet extends HttpServlet {

    @EJB
    private RouteService routeService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/routes/add_routes.jsp");
//        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String startingPoint = request.getParameter("startingPoint");
        String destinationPoint = request.getParameter("destinationPoint");

        if (name != null && !name.isEmpty() && startingPoint != null && !startingPoint.isEmpty()
                && destinationPoint != null && !destinationPoint.isEmpty()) {
            boolean success = routeService.registerRoute(new Destination(),name, startingPoint, destinationPoint);

            if (success) {
                request.setAttribute("success_message", "Route successfully registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("success_page/common_page.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error_message", "Failed to register route. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("error_message", "Name, starting point, and destination point are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error_page/common_page.jsp");
            dispatcher.forward(request, response);
        }
    }
}