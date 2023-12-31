package org.zerock.jdbcex.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController",value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet
{
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log.info("todo list.......................");

        try {
            List<TodoDTO> dtoList = todoService.listAll();
            request.setAttribute("dtoList", dtoList);
            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request,response);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }
}

//p.150 localhost:8080/todo/list
//        404에러
//        Tomcat 9.0.79
//        - edit configuration - Deployment - "+" - / 하면 해결됨.
//        14:55:12.493 [http-nio-8080-exec-7] INFO  org.zerock.jdbcex.controller.TodoListController - todo list.......................

