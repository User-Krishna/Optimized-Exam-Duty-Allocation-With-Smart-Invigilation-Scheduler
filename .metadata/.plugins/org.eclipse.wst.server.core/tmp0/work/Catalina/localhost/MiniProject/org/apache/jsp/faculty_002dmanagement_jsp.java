/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.28
 * Generated at: 2024-11-16 05:36:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.sql.*;
import com.gnit.DatabaseFile.DbConnection;

public final class faculty_002dmanagement_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = new java.util.LinkedHashSet<>(2);
    _jspx_imports_classes.add("com.gnit.DatabaseFile.DbConnection");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Faculty Management</title>\r\n");
      out.write("    <style>\r\n");
      out.write("       /* General styles */\r\n");
      out.write("body {\r\n");
      out.write("    margin: 0px;\r\n");
      out.write("    padding: 0px;\r\n");
      out.write("    font-family: 'Arial', sans-serif; /* Set a modern font family */\r\n");
      out.write("    background-image: url('images/faculty-availability.jpg');\r\n");
      out.write("    background-size: cover; /* Ensure the background covers the entire area */\r\n");
      out.write("    background-position: center;\r\n");
      out.write("    background-attachment: fixed;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Box styling for the container */\r\n");
      out.write(".container {\r\n");
      out.write("    background-color: rgba(228, 224, 225, 0.7); /* Decreased transparency of box background */\r\n");
      out.write("    padding: 20px;\r\n");
      out.write("    border-radius: 10px;\r\n");
      out.write("    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Soft shadow for the box */\r\n");
      out.write("    margin: 20px; /* Margin to give space around the box */\r\n");
      out.write("    max-width: 800px; /* Decreased width of the container */\r\n");
      out.write("    margin-left: auto; /* Center the container */\r\n");
      out.write("    margin-right: auto; /* Center the container */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Heading styles */\r\n");
      out.write("h1, h2 {\r\n");
      out.write("    color: black; /* Set text color to black */\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    font-size: 28px; /* Normal font size */\r\n");
      out.write("    transition: font-size 0.3s ease; /* Transition for smooth effect */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Enlarge text on heading hover */\r\n");
      out.write("h1:hover, h2:hover {\r\n");
      out.write("    font-size: 32px; /* Increase font size on hover */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Form input fields */\r\n");
      out.write("input[type=\"number\"], input[type=\"text\"], input[type=\"email\"], select {\r\n");
      out.write("    padding: 12px;\r\n");
      out.write("    margin-bottom: 15px;\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    box-sizing: border-box;\r\n");
      out.write("    border: 2px solid #D5B4B4; /* Updated border color */\r\n");
      out.write("    border-radius: 8px;\r\n");
      out.write("    font-size: 18px; /* Normal font size */\r\n");
      out.write("    background-color: #F5EBEB; /* Light background for form fields */\r\n");
      out.write("    transition: border-color 0.3s ease, box-shadow 0.3s ease;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"number\"]:focus, input[type=\"text\"]:focus, input[type=\"email\"]:focus, select:focus {\r\n");
      out.write("    border-color: #80bdff;\r\n");
      out.write("    box-shadow: 0 0 8px rgba(0, 123, 255, 0.5); /* Blue shadow on focus */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Button styling */\r\n");
      out.write("input[type=\"submit\"] {\r\n");
      out.write("    background-color: #49243E; /* Dark color for buttons */\r\n");
      out.write("    color: white;\r\n");
      out.write("    border: none;\r\n");
      out.write("    border-radius: 4px;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("    padding: 8px 12px; /* Smaller padding for button size */\r\n");
      out.write("    font-size: 14px; /* Normal button font size */\r\n");
      out.write("    transition: background-color 0.3s ease; /* Adding transition effects */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"submit\"]:hover {\r\n");
      out.write("    background-color: #331729; /* Darker shade on hover */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Table styling */\r\n");
      out.write("table {\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    border-collapse: collapse;\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th, td {\r\n");
      out.write("    border: 1px solid #D5B4B4; /* Updated border color */\r\n");
      out.write("    padding: 12px; /* Increased padding for better spacing */\r\n");
      out.write("    text-align: left;\r\n");
      out.write("    color: black; /* Set text color to black */\r\n");
      out.write("    font-size: 18px; /* Normal font size for table cells */\r\n");
      out.write("    transition: color 0.3s ease, transform 0.3s ease; /* Adding transition effects */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Table header styles */\r\n");
      out.write("th {\r\n");
      out.write("    background-color: #49243E; /* Dark color for table header */\r\n");
      out.write("    color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Hover effect only on text in table */\r\n");
      out.write("td:hover {\r\n");
      out.write("    color: #867070; /* Change text color on hover */\r\n");
      out.write("    transform: scale(1.1); /* Slightly enlarges text on hover */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Styling for the button container */\r\n");
      out.write(".button-container {\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* Styling for the back button */\r\n");
      out.write(".back-btn {\r\n");
      out.write("    background-color: #49243E; /* Dark color for back button */\r\n");
      out.write("    color: white;\r\n");
      out.write("    padding: 10px 15px; /* Normal padding for back button size */\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    border-radius: 4px;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    transition: background-color 0.3s ease; /* Adding transition effects */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".back-btn:hover {\r\n");
      out.write("    background-color: #7E6363; /* Darker shade on hover */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <h1>Faculty Management</h1>\r\n");
      out.write("\r\n");
      out.write("        <!-- Form for Adding Faculty -->\r\n");
      out.write("        <form method=\"post\" action=\"faculty-management.jsp\">\r\n");
      out.write("            <h2>Add Faculty</h2>\r\n");
      out.write("            <input type=\"number\" name=\"id\" placeholder=\"ID\" required>\r\n");
      out.write("            <input type=\"text\" name=\"name\" placeholder=\"Name\" required>\r\n");
      out.write("            <input type=\"text\" name=\"department\" placeholder=\"Department\" required>\r\n");
      out.write("            <input type=\"email\" name=\"email\" placeholder=\"Email\" required>\r\n");
      out.write("            <input type=\"submit\" name=\"action\" value=\"Add Faculty\">\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        <!-- Form for Searching and Editing Faculty -->\r\n");
      out.write("        <form method=\"get\" action=\"faculty-management.jsp\">\r\n");
      out.write("            <h2>Search and Edit Faculty</h2>\r\n");
      out.write("            <input type=\"text\" name=\"searchId\" placeholder=\"Search by ID\">\r\n");
      out.write("            <input type=\"submit\" value=\"Search\">\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        ");
 
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String action = request.getParameter("action");
            try {
                // Get database connection
                conn = DbConnection.getConnection();

                // Handle Add Faculty
                if ("Add Faculty".equals(action)) {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String department = request.getParameter("department");
                    String email = request.getParameter("email");
                    
                    // Check if ID already exists
                    String checkIdQuery = "SELECT COUNT(*) FROM faculty WHERE id = ?";
                    stmt = conn.prepareStatement(checkIdQuery);
                    stmt.setInt(1, Integer.parseInt(id));
                    rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        out.println("<p>ID already exists!</p>");
                    } else {
                        String insertQuery = "INSERT INTO faculty (id, name, department, email) VALUES (?, ?, ?, ?)";
                        stmt = conn.prepareStatement(insertQuery);
                        stmt.setInt(1, Integer.parseInt(id));
                        stmt.setString(2, name);
                        stmt.setString(3, department);
                        stmt.setString(4, email);
                        stmt.executeUpdate();
                        out.println("<p>Faculty added successfully!</p>");
                    }
                }

                // Handle Search and Edit Faculty
                String searchId = request.getParameter("searchId");
                if (searchId != null && !searchId.isEmpty()) {
                    String selectQuery = "SELECT * FROM faculty WHERE id = ?";
                    stmt = conn.prepareStatement(selectQuery);
                    stmt.setInt(1, Integer.parseInt(searchId));
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String department = rs.getString("department");
                        String email = rs.getString("email");
        
      out.write("\r\n");
      out.write("        <!-- Edit Faculty Form -->\r\n");
      out.write("        <form method=\"post\" action=\"faculty-management.jsp\">\r\n");
      out.write("            <h2>Edit Faculty</h2>\r\n");
      out.write("            <input type=\"hidden\" name=\"id\" value=\"");
      out.print( id );
      out.write("\">\r\n");
      out.write("            <input type=\"text\" name=\"name\" value=\"");
      out.print( name );
      out.write("\" required>\r\n");
      out.write("            <input type=\"text\" name=\"department\" value=\"");
      out.print( department );
      out.write("\" required>\r\n");
      out.write("            <input type=\"email\" name=\"email\" value=\"");
      out.print( email );
      out.write("\" required>\r\n");
      out.write("            <input type=\"submit\" name=\"action\" value=\"Update Faculty\">\r\n");
      out.write("        </form>\r\n");
      out.write("        ");
 
                    } else {
                        out.println("<p>Faculty not found!</p>");
                    }
                }

                // Handle Update Faculty
                if ("Update Faculty".equals(action)) {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String department = request.getParameter("department");
                    String email = request.getParameter("email");
                    String updateQuery = "UPDATE faculty SET name = ?, department = ?, email = ? WHERE id = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, name);
                    stmt.setString(2, department);
                    stmt.setString(3, email);
                    stmt.setInt(4, Integer.parseInt(id));
                    stmt.executeUpdate();
                    out.println("<p>Faculty updated successfully!</p>");
                }

                // Handle Delete Faculty
               if ("Delete Faculty".equals(action)) {
    String id = request.getParameter("id");

    // Delete related records in the availability table first
    String deleteAvailabilityQuery = "DELETE FROM availability WHERE faculty_id = ?";
    stmt = conn.prepareStatement(deleteAvailabilityQuery);
    stmt.setInt(1, Integer.parseInt(id));
    stmt.executeUpdate();

    // Now delete the faculty record
    String deleteQuery = "DELETE FROM faculty WHERE id = ?";
    stmt = conn.prepareStatement(deleteQuery);
    stmt.setInt(1, Integer.parseInt(id));
    stmt.executeUpdate();

    out.println("<p>Faculty deleted successfully!</p>");
}


                // Fetch all faculty records for display
                String selectAllQuery = "SELECT * FROM faculty";
                stmt = conn.prepareStatement(selectAllQuery);
                rs = stmt.executeQuery();
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- Displaying Faculty List -->\r\n");
      out.write("        <h2>Faculty List</h2>\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>ID</th>\r\n");
      out.write("                <th>Name</th>\r\n");
      out.write("                <th>Department</th>\r\n");
      out.write("                <th>Email</th>\r\n");
      out.write("                <th>Actions</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
 while (rs.next()) { 
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print( rs.getString("id") );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( rs.getString("name") );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( rs.getString("department") );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( rs.getString("email") );
      out.write("</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <div class=\"button-container\">\r\n");
      out.write("                        <form method=\"post\" action=\"faculty-management.jsp\" style=\"display:inline;\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"id\" value=\"");
      out.print( rs.getString("id") );
      out.write("\">\r\n");
      out.write("                            <input type=\"submit\" name=\"action\" value=\"Delete Faculty\" class=\"delete-button\">\r\n");
      out.write("                        </form>\r\n");
      out.write("                        <form method=\"get\" action=\"faculty-management.jsp\" style=\"display:inline;\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"searchId\" value=\"");
      out.print( rs.getString("id") );
      out.write("\">\r\n");
      out.write("                            <input type=\"submit\" value=\"Edit Faculty\" class=\"edit-button\">\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
 } 
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("        ");
 
            } catch (SQLException e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
      out.write("\r\n");
      out.write("        <div class=\"button-container\">\r\n");
      out.write("            <a href=\"landing.html\" class=\"back-btn\">Back to Home</a> <!-- Back to Home Button -->\r\n");
      out.write("        </div>\r\n");
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
