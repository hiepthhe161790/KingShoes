    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOANGHIEP
 */
public class CartControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String id = request.getParameter("id");
    Cookie[] arr = request.getCookies();
    List<Cookie> cookiesToDelete = new ArrayList<>();

    // Xóa các cookie có tên là "id"
    if (arr != null) {
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                cookiesToDelete.add(o);
            }
        }
    }

    // Xóa các cookie cần xóa
    for (Cookie cookie : cookiesToDelete) {
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    // Tạo cookie mới cho mỗi sản phẩm
    if (id != null && !id.isEmpty()) {
        String[] idArr = id.split(",");
        for (String productId : idArr) {
            Cookie c = new Cookie("id", productId);
            c.setMaxAge(60*60*24);
            response.addCookie(c);
        }
    }

    response.sendRedirect("ShowCartControl");
}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
//  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        String id = request.getParameter("id");
//        Cookie arr[] = request.getCookies();
//        String txt = "";
//        for (Cookie o : arr) {
//            if(o.getName().equals("id")){
//                txt = txt + o.getValue();
//                o.setMaxAge(0);
//                response.addCookie(o);
//            }
//            
//        }
//        if(txt.isEmpty()){
//            txt = id;
//            
//        }else{
//            txt = txt + "," + id;
//            
//        }
//        
//        Cookie c = new Cookie("id", txt);
//        c.setMaxAge(60*60*24);
//        response.addCookie(c);
//        response.sendRedirect("ShowCartControl");
//    } 