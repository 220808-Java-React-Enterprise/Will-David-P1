package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class FinanceManagerServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;
    private final ReimbursementService reimbursementService;

    public FinanceManagerServlet(ObjectMapper mapper, TokenService tokenService, UserService userService, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
        this.reimbursementService = reimbursementService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        PrincipalResponse principal = tokenService.extractRequesterDetails(token);

        resp.setContentType("application/json");

        try {

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("viewreimbs")) {
                if (principal.getRole().equals("y")) {
                    List<ERSReimbursements> reims = reimbursementService.listAllReimbursements();
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(reims));
                }

            } else {
                System.out.println("No");
            }

        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        String decision = req.getHeader("ApproveDeny");
        String reimID = req.getHeader("ReimID");
        PrincipalResponse principal = tokenService.extractRequesterDetails(token);

        resp.setContentType("application/json");

        try {

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("approvedeny")) {
                if (principal.getRole().equals("y")) {
                    if (decision.equals("Approve")) {
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        reimbursementService.updateStatus(reimID, principal.getuName(), "2");
                        resp.getWriter().write("Approved");
                    } else if (decision.equals("Reject")) {
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        reimbursementService.updateStatus(reimID, principal.getuName(), "3");
                        resp.getWriter().write("Rejected");
                    }
                }

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
