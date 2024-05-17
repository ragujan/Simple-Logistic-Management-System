package com.jiat.web.servlet.merchant;

import com.jiat.ejb.entity.Orders;
import com.jiat.ejb.remote.AddOrderToFreight;
import com.jiat.ejb.remote.OrderRetrievalService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/assign-order")
public class AssignOrderToFreightServlet extends HttpServlet {

    @EJB
    OrderRetrievalService orderRetrievalService;

    @EJB
    AddOrderToFreight addOrderToFreight;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Orders order = orderRetrievalService.retrieveOrderById("1");
        System.out.println("order is "+order.getId());
        addOrderToFreight.addOrderToAvailableFreight(order);
    }



}
