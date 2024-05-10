package com.jiat.web.servlet;

import com.jiat.ejb.remote.TimerService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    @EJB
    TimerService timerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String duration = request.getParameter("duration");

        timerService.schedule(Long.parseLong(duration));

    }
}
