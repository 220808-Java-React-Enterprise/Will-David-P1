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
import java.net.HttpRetryException;
import java.sql.SQLException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write(mapper.writeValueAsString(req));

        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            if (!request.getRole().equals("x")||!request.getRole().equals("y")) {
                resp.setStatus(404);
            }

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("signup")) {
                ERSUsers newUser = userService.register(request);
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(newUser.getuID()));
            } else {
                System.out.println("No");
                resp.setStatus(404);
            }

        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("Authorization");
        PrincipalResponse principal = tokenService.extractRequesterDetails(token);

        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(principal));

        try {
            if (principal.getRole().equals("z")) {
                String username = req.getParameter("username");

                resp.setContentType("application/json");
                if (username != null) {
                    resp.getWriter().write(mapper.writeValueAsString(userService.getUserByUsername(username)));
                } else {
                    List<ERSUsers> userList = userService.getAllAccounts();
                    resp.getWriter().write(mapper.writeValueAsString(userList));
                }
            } else {
                resp.setStatus(403); // FORBIDDEN
            }
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }


    }
}
