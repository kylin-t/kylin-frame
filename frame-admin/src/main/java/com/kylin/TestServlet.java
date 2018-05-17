package com.kylin;

import com.kylin.test.ModulesDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("aaa");
        PrintWriter out = resp.getWriter();
        ModulesDemo modulesDemo = new ModulesDemo();

        String str = modulesDemo.getString();

        out.print("aaa");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("aaa");
        PrintWriter out = resp.getWriter();
//        ModulesDemo modulesDemo = new ModulesDemo();
//
//        String str = modulesDemo.getString();

        out.print("aaa");
        out.flush();
        out.close();
    }
}
