/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2024-11-08 18:02:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import com.gnit.servlet.FacultyAllocationServlet.Allocation;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import com.gnit.DatabaseFile.DbConnection;

public final class display_002dallocations_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(6);
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(6);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.gnit.DatabaseFile.DbConnection");
    _jspx_imports_classes.add("com.gnit.servlet.FacultyAllocationServlet.Allocation");
    _jspx_imports_classes.add("java.util.ArrayList");
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Faculty Allocation Results</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/display-allocations.css\">\r\n");
      out.write("        <style>\r\n");
      out.write("        body {\r\n");
      out.write("    font-family: Arial, sans-serif;\r\n");
      out.write("    margin: 0;\r\n");
      out.write("    padding: 0;\r\n");
      out.write("    background: rgba(248, 249, 250, 0.8);\r\n");
      out.write("    background-image: url('images/5.jpg');\r\n");
      out.write("    background-size: cover;\r\n");
      out.write("    background-position: center;\r\n");
      out.write("    background-attachment: fixed;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("table {\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    border-collapse: collapse;\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th, td {\r\n");
      out.write("    padding: 10px;\r\n");
      out.write("    border: 1px solid #ddd;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th {\r\n");
      out.write("    background-color: #49243E;\r\n");
      out.write("    color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("tr:nth-child(even) {\r\n");
      out.write("    background-color: #f2f2f2;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".container {\r\n");
      out.write("    max-width: 1000px;\r\n");
      out.write("     margin: 0 auto;\r\n");
      out.write("    margin-top: 100px;\r\n");
      out.write("    background: rgba(255, 255, 255, 0.9); /* White background with 90% opacity */\r\n");
      out.write("    padding: 20px;\r\n");
      out.write("    border-radius: 8px;\r\n");
      out.write("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".edit-form {\r\n");
      out.write("    display: inline-flex;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("    gap: 5px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".edit-form input {\r\n");
      out.write("    width: 100px;\r\n");
      out.write("    padding: 5px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".edit-form button {\r\n");
      out.write("    background: url('edit-icon.png') no-repeat center;\r\n");
      out.write("    background-size: 20px 20px;\r\n");
      out.write("    border: none;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("    padding: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".edit-form button:focus {\r\n");
      out.write("    outline: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".error {\r\n");
      out.write("    color: red;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".success {\r\n");
      out.write("    color: green;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buttons {\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button {\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    padding: 10px 20px;\r\n");
      out.write("    margin: 0 10px;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    color: white;\r\n");
      out.write("    background-color: #331729;\r\n");
      out.write("    border-radius: 5px;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button:hover {\r\n");
      out.write("    background-color: #49243E;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <h1>Faculty Allocation Results</h1>\r\n");
      out.write("\r\n");
      out.write("        ");

            String examDate = request.getParameter("examDate");
            List<Allocation> allocations = null;
            String errorMessage = "";
            String successMessage = "";

            // Handle room number update
            if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("updateRoomNumber") != null) {
                String facultyIdStr = request.getParameter("facultyId");
                String oldRoomNumber = request.getParameter("oldRoomNumber");
                String newRoomNumber = request.getParameter("newRoomNumber");

                Connection conn = null;
                PreparedStatement stmt = null;

                try {
                    conn = DbConnection.getConnection();
                    String updateQuery = "UPDATE faculty_allocation SET room_number = ? WHERE faculty_id = ? AND room_number = ? AND exam_date = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, newRoomNumber);
                    stmt.setInt(2, Integer.parseInt(facultyIdStr));
                    stmt.setString(3, oldRoomNumber);
                    stmt.setDate(4, java.sql.Date.valueOf(examDate));
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        successMessage = "Room number updated successfully!";
                    } else {
                        errorMessage = "No records updated. Please check the details.";
                    }
                } catch (SQLException e) {
                    errorMessage = "Error updating room number: " + e.getMessage();
                } finally {
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }

            // Fetch updated allocations
            if (examDate != null) {
                try (Connection conn = DbConnection.getConnection()) {
                    String sql = "SELECT a.room_number, f.name, a.faculty_id FROM faculty_allocation a JOIN faculty f ON a.faculty_id = f.id WHERE a.exam_date = ? AND a.archived = false";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setDate(1, java.sql.Date.valueOf(examDate));
                        try (ResultSet rs = stmt.executeQuery()) {
                            allocations = new ArrayList<>();
                            while (rs.next()) {
                                String roomNumber = rs.getString("room_number"); // Handle room_number as String
                                int facultyId = rs.getInt("faculty_id");
                                allocations.add(new Allocation(roomNumber, facultyId)); // Use String for room_number
                            }
                        }
                    }
                } catch (SQLException e) {
                    errorMessage = "Error fetching allocations: " + e.getMessage();
                }
            }
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"");
      out.print( errorMessage.isEmpty() ? "" : "error" );
      out.write("\">\r\n");
      out.write("            ");
      out.print( errorMessage );
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"");
      out.print( successMessage.isEmpty() ? "" : "success" );
      out.write("\">\r\n");
      out.write("            ");
      out.print( successMessage );
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <table>\r\n");
      out.write("            <thead>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>Room Number</th>\r\n");
      out.write("                    <th>Faculty ID</th>\r\n");
      out.write("                    <th>Faculty Name</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </thead>\r\n");
      out.write("            <tbody>\r\n");
      out.write("                ");
 
                    if (allocations != null && !allocations.isEmpty()) {
                        for (Allocation allocation : allocations) {
                            String roomNumber = allocation.getRoomCode();
                            int facultyId = allocation.getFacultyId();
                            
                            // Query to get faculty name
                            Connection connForName = null;
                            PreparedStatement stmtForName = null;
                            ResultSet rsForName = null;
                            String facultyName = "";

                            try {
                                connForName = DbConnection.getConnection();
                                String nameQuery = "SELECT name FROM faculty WHERE id = ?";
                                stmtForName = connForName.prepareStatement(nameQuery);
                                stmtForName.setInt(1, facultyId);
                                rsForName = stmtForName.executeQuery();
                                if (rsForName.next()) {
                                    facultyName = rsForName.getString("name");
                                }
                            } catch (SQLException e) {
                                out.println("<p>Error fetching faculty names: " + e.getMessage() + "</p>");
                            } finally {
                                try { if (rsForName != null) rsForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                                try { if (stmtForName != null) stmtForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                                try { if (connForName != null) connForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                            }
                
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <!-- Display room number with edit option -->\r\n");
      out.write("                        <form method=\"post\" action=\"display-allocations.jsp\" class=\"edit-form\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"facultyId\" value=\"");
      out.print( facultyId );
      out.write("\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"oldRoomNumber\" value=\"");
      out.print( roomNumber );
      out.write("\">\r\n");
      out.write("                            <input type=\"text\" name=\"newRoomNumber\" value=\"");
      out.print( roomNumber );
      out.write("\" required>\r\n");
      out.write("                            <input type=\"hidden\" name=\"examDate\" value=\"");
      out.print( examDate );
      out.write("\">\r\n");
      out.write("                            <button type=\"submit\" name=\"updateRoomNumber\" \r\n");
      out.write("    style=\"background: none; color: #333; border: none; font-size: 16px; cursor: pointer; padding: 8px 16px; transition: color 0.3s ease, text-decoration 0.3s ease;\"\r\n");
      out.write("    onmouseover=\"this.style.color='#007bff'; this.style.textDecoration='underline';\"\r\n");
      out.write("    onmouseout=\"this.style.color='#333'; this.style.textDecoration='none';\">\r\n");
      out.write("    Update\r\n");
      out.write("</button>\r\n");
      out.write("                            \r\n");
      out.write("                        </form>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td>");
      out.print( facultyId );
      out.write("</td>\r\n");
      out.write("                    <td>");
      out.print( facultyName );
      out.write("</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");
 
                        }
                    } else {
                        out.println("<p>No allocations to display.</p>");
                    }
                
      out.write("\r\n");
      out.write("            </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("           <div class=\"buttons\">\r\n");
      out.write("                <a href=\"faculty-allocation-form.jsp\" class=\"button\">Back to Allocation Form</a>\r\n");
      out.write("                <a href=\"notification-center.jsp\" class=\"button\">Goto Notification Center</a>\r\n");
      out.write("                \r\n");
      out.write("</div>\r\n");
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