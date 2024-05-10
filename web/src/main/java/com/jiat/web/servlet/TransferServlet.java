package com.jiat.web.servlet;

import com.jiat.ejb.remote.AccountService;
import com.jiat.ejb.remote.TransferService;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    @EJB
    private TransferService transferService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAccountNo = request.getParameter("sourceAccountNo");
        String destinationAccountNo = request.getParameter("destinationAccountNo");
        String amount = request.getParameter("amount");

        try{
            transferService.transferAmount(sourceAccountNo,destinationAccountNo,Double.parseDouble(amount));
        }catch (Exception ex){

        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
