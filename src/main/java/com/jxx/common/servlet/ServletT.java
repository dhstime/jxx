package com.jxx.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class ServletT extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.write("<h1>Hello Servlet");
        out.close();
    }
}
