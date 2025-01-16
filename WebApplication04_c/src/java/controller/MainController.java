/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADD
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

  public boolean isValidLogin(String uname,String pass){
      if(uname.equals("admin")&&pass.equals("12345678")){
          return true;
          
      }
      return false;
      
  }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = new RequestDispatcher() {
            @Override
            public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          String UN=request.getParameter("txtUName");
           String pass=request.getParameter("txtPass");
           
           //Kiem tra null
           if(UN==null||UN.trim().length()==0){
               out.println("Username cannot blank");
               return;
           }
           if(pass==null||pass.trim().length()==0){
               out.println("Pass cannot blank");
               return;
           }
           //kiem tra du 8 ky tu 
           
           if(pass.trim().length()<8){
               out.println("pass must >=8 char");
               return;
           }
           if(isValidLogin(UN, pass)){
                rd = request.getRequestDispatcher("Search.html");
                rd.forward(request, response);
                //chuyển tiếp thông tin nội bộ trong dự án 
                //Chuyển tiếp dữ liệu (ở đây bao gồm các thong tin như Username và Password)
                //Không thay đổi URL ( Nội bộ) 
           }else{
               rd=request.getRequestDispatcher("Invalid.html");
               response.sendRedirect("Invalid.html");
               //Thay đổi URL
               //Không chuyển tiếp dữ liệu , chỉ đơn thuần là chuyển hướng
               //có thể chuyển tiếp ngoài dự án dựa vào URL
           }
        }}
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
