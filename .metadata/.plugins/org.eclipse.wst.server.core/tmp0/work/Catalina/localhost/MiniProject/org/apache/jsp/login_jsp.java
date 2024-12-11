/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2024-11-11 08:11:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">\r\n");
      out.write("    <title>Login</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            background-image: url(images/clz2.jpg);\r\n");
      out.write("            display: flex;\r\n");
      out.write("            flex-direction: column;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            justify-content: center;\r\n");
      out.write("            height: 100vh;\r\n");
      out.write("            margin: 0;\r\n");
      out.write("            background-color: #AEFD6C;\r\n");
      out.write("            background-size: cover;\r\n");
      out.write("            background-position: center center;\r\n");
      out.write("            background-repeat: no-repeat;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #cameraModal {\r\n");
      out.write("            display: none;\r\n");
      out.write("            position: fixed;\r\n");
      out.write("            z-index: 1;\r\n");
      out.write("            padding-top: 60px;\r\n");
      out.write("            left: 0;\r\n");
      out.write("            top: 0;\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            height: 100%;\r\n");
      out.write("            background-color: rgba(0,0,0,0.4);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #cameraModalContent {\r\n");
      out.write("            background-color: #fefefe;\r\n");
      out.write("            margin: auto;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            border: 1px solid #888;\r\n");
      out.write("            width: 60%;\r\n");
      out.write("            max-width: 300px;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        video {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            max-width: 250px;\r\n");
      out.write("            border: 1px solid black;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #capture, #closeCamera {\r\n");
      out.write("            margin: 10px 5px;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <h1>Login</h1>\r\n");
      out.write("        <form action=\"LoginServlet\" method=\"post\" id=\"loginForm\">\r\n");
      out.write("            <label for=\"username\">Username:</label>\r\n");
      out.write("            <input type=\"text\" id=\"username\" name=\"username\" required><br>\r\n");
      out.write("            <label for=\"password\">Password:</label>\r\n");
      out.write("            <input type=\"password\" id=\"password\" name=\"password\" required><br>\r\n");
      out.write("            <button type=\"submit\">Login</button>\r\n");
      out.write("        </form>\r\n");
      out.write("<p>Or<p>\r\n");
      out.write("        <!-- New 'Login with Face' button -->\r\n");
      out.write("        <form action=\"http://localhost:5173/user-select\" method=\"get\">\r\n");
      out.write("            <button type=\"submit\" style=\"margin-top: 10px;\">Login with Face</button>\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        <!-- Error message handling -->\r\n");
      out.write("        ");
 String error = request.getParameter("error");
        if (error != null && error.equals("1")) { 
      out.write("\r\n");
      out.write("            <p style=\"color: red;\">Invalid username or password. Please try again.</p>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
 String rs = request.getParameter("registration");
        if (rs != null && rs.equals("success")) { 
      out.write("\r\n");
      out.write("            <p style=\"color: green;\">Your Registration is Successful. Please Login.</p>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}