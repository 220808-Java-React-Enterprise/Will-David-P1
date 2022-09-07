package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.requests.NewUserRequest;
import com.revature.P1.dtos.responses.PrincipalResponse;
import com.revature.P1.models.ERSReimbursements;
import com.revature.P1.models.ERSUsers;
import com.revature.P1.services.ReimbursementService;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ReimbursementServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    private final ReimbursementService reimbursementService;

    public ReimbursementServlet(ObjectMapper mapper, TokenService tokenService, UserService userService, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
        this.reimbursementService = reimbursementService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("User");
        PrincipalResponse principal = tokenService.extractRequesterDetails(token);
        String type = req.getHeader("ReimType");

        try {

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("newrequest")) {
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                ERSReimbursements reim = new ERSReimbursements(UUID.randomUUID().toString(), 10, sqlDate, sqlDate, "hello", 100000, "123", principal.getuID(), "a", "pending", type);
                reimbursementService.reimSave(reim);
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(reim));
            } else {
                System.out.println("No");
            }

        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        }

    }
}
