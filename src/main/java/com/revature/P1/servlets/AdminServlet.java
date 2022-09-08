package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.requests.ResetPasswordReq;
import com.revature.P1.dtos.responses.PrincipalResponse;
import com.revature.P1.models.ERSUsers;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private final ObjectMapper mapper;

    private final TokenService tokenService;
    private final UserService userService;

    public AdminServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            String userID = req.getHeader("UserID");

            PrincipalResponse principal = tokenService.extractRequesterDetails(token);
            String[] p = req.getRequestURI().split("/");

            if (principal.getRole().equals("z")) {
                switch (p[3]) {
                    case "activate":
                        userService.activate(userID, true);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString("Account activated!"));
                        break;
                    case "deactivate":
                        userService.activate(userID, false);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString("Account deactivated!"));
                        break;
                    case "reset_password":
                        ResetPasswordReq passRequest = mapper.readValue(req.getInputStream(), ResetPasswordReq.class);
                        System.out.println(passRequest);
                        userService.resetPassword(passRequest);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString("Account's password reset successfully!"));
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            }else{
                resp.setStatus(403);
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        } catch (Exception e) {
            resp.setStatus(404);
        }
    }


}