/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountControl;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HOANGHIEP
 */
@WebServlet(name = "UpdateControl", urlPatterns = {"/update"})
public class UpdateControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String xId = request.getParameter("id");
        DAO u = new DAO();
        Account account = u.getAccountByID(xId);
        if(account==null) {
           pr.println("<h2> A student is not found</h2>");
           request.getRequestDispatcher("update.html").include(request, response);
        }
        else {
           request.setAttribute("account", account);
           request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
        }

    }
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter pr = response.getWriter();
        String sId, suser, spass, sisSell, sisAdmin;
        int isSell, isAdmin;
       sId = request.getParameter("id");
        suser = request.getParameter("user");
          spass = request.getParameter("pass");
        boolean isOk = true;
        if (sId == null || sId.equals("")) {
            isOk = false;
        }
        if (suser == null || suser.trim().length() == 0 || suser.trim().equals("Invalid name!")) {
            suser = "Invalid name!";
            isOk = false;
        }
         if (spass == null || spass.trim().length() == 0 || spass.trim().equals("Invalid pass!")) {
            spass = "Invalid pass!";
            isOk = false;
        }
        sisSell = request.getParameter("sell");
        if (sisSell == null || sisSell.trim().length() == 0) {
            isSell = 0;
        } else {
            isSell = Integer.parseInt(sisSell.trim());
        }
      sisAdmin = request.getParameter("admin");
        if (sisAdmin == null || sisAdmin.trim().length() == 0) {
            isSell = 0;
        } else {
            isSell = Integer.parseInt(sisAdmin.trim());
        }
        
        
        isAdmin = Integer.parseInt(request.getParameter("isAdmin"));

        Account account = new Account();
        account.setId(Integer.parseInt(sId));
        account.setUser(suser);
        account.setPass(spass);
        account.setIsSell(isSell);
        account.setIsAdmin(isAdmin);

        DAO dao = new DAO();
        dao.updateAccount(account);

        List<Account> accountList = dao.getAllAccounts();
        request.setAttribute("lst", accountList);
        request.getRequestDispatcher("ManagerAccount").forward(request, response);
    }

//    <%@page contentType="text/html" pageEncoding="UTF-8"%>
//<%@page import="model.*" %>
//<!DOCTYPE html>
//<html>
//  <head>
//        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
//        <title>Search result</title>
//  </head>
//  <body>
//    <%
//        Account account = (Account) request.getAttribute("account");
//        if (account == null)
//            return;
//    %>
//      <h2> Update a student </h2>
//       <form action="update" method="GET">
//          <p>Enter id:
//          <input type="text" name="id" value=""/>
//          <input type="submit" value="Search"/>
//       </form>
//    <h2>Update Account</h2>
//    <form action="update" method="POST">
//        <p><input type="hidden" name="id" value="<%= account.getId() %>" />
//        <p><input type="text" name="user" value="<%= account.getUser() %>" />
//        <p><input type="password" name="pass" value="<%= account.getPass() %>" />
//        <p><input type="text" name="isSell" value="<%= account.getIsSell() %>" />
//        <p><input type="text" name="isAdmin" value="<%= account.getIsAdmin() %>" />
//        <p><input type="submit" value="Update">
//    </form>
//    <p><button onclick="window.history.go(-1);">Back to previous page</button>
//    <p><a href="index.html">Back to homepage</a>
//</body>
//
//</html>


}
