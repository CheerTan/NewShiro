package com.learn.shirologin.servlet;

import com.learn.shirologin.model.Controller;
import com.learn.shirologin.service.ControllerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value= "/ReleaseServlet")
public class ReleaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        byte status=Byte.parseByte(request.getParameter("status"));
        Controller controller=new Controller();
        controller.setStatus(status);
        if(new ControllerService().release(controller)){
            System.out.println("Good!");
//            response.sendRedirect("../examList.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
