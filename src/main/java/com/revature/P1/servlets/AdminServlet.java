package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.requests.NewUserRequest;
import com.revature.P1.dtos.responses.PrincipalResponse;
import com.revature.P1.models.ERSUsers;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.ResourceConflictException;
import com.revature.P1.utils.database.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.revature.P1.dtos.requests.*;

public class AdminServlet extends HttpServlet {
    private final ObjectMapper mapper;

    private final UserService userService;
    private final TokenService tokenService;

    public AdminServlet(ObjectMapper mapper, UserService userService, TokenService tokenService) {
        this.mapper = mapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");


            PrincipalResponse principal = tokenService.extractRequesterDetails(token);
            ERSUsers user;
            String[] p = req.getRequestURI().split("/");

            if (principal.getRole().equals("1")) {
                switch (p[3]) {
                    case "activate":
                        UserRequest r = mapper.readValue(req.getInputStream(), UserRequest.class);
                        userService.activate(r);
                        user = userService.getById(r.getuID());
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString(user));

                        break;
                    case "deactivate":
                        r = mapper.readValue(req.getInputStream(), UserRequest.class);
                        userService.deactivate(r);
                        user = userService.getById(r.getuID());
                        resp.setStatus(200);
                        resp.setContentType("application/json");

                        resp.getWriter().write(mapper.writeValueAsString(user));

                        break;
                    case "reset_password":
                        ResetPasswordReq passRequest = mapper.readValue(req.getInputStream(), ResetPasswordReq.class);

                        userService.resetPassword(passRequest);
                        user = userService.getById(passRequest.getId());
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString(user));
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        PrincipalResponse principal = tokenService.extractRequesterDetails(token);
        if (principal.getRole().equals("1")) {


            try {
                NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);

                String[] path = req.getRequestURI().split("/");

                if (path[3].equals("signup")) {
                    ERSUsers user = userService.register(request);

                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(user));
                } else {
                    System.out.println("Error");
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
        else{
            resp.setStatus(403);
        }
    }

}
