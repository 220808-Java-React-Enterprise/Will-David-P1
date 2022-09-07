package com.revature.P1.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.daos.ReimbursementDAO;
import com.revature.P1.daos.UserDAO;
import com.revature.P1.services.ReimbursementService;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.servlets.AuthServlet;
import com.revature.P1.servlets.ReimbursementServlet;
import com.revature.P1.servlets.TestServlet;
import com.revature.P1.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class ContextLoaderListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ObjectMapper mapper = new ObjectMapper();

        TestServlet testServlet = new TestServlet();
        UserServlet userServlet = new UserServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()));
        AuthServlet authServlet = new AuthServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()));
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()), new ReimbursementService(new ReimbursementDAO()));


        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down application!");
    }
}
