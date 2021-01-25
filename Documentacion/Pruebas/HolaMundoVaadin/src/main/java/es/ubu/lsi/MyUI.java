package es.ubu.lsi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.swing.JFrame;
import javax.xml.crypto.Data;

import java.io.*;  
import java.sql.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
  
        try {  
            Class.forName ( "sun.jdbc.odbc.JdbcOdbcDriver" );  
            Connection con = DriverManager.getConnection ( "jdbc: odbc: prueba" );  
            Statement st = con.createStatement ();  
            ResultSet rs = st.executeQuery ( "Select * from N1_Tribunal" );      
            while  (rs.next ()){  
                System.out.print ("hola");  
            }  
            
            st.close ();  
            con.close (); 
            
            name.setCaption("Pruebas");        
	        layout.addComponents(name);
	        layout.setMargin(true);
	        layout.setSpacing(true);
	        setContent(layout);  
	        
        }  catch  (Exception ex) {  
            System.err.print ( "Excepción:" );  
            System.err.println (ex.getMessage ());  
        }  	        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
    }
}
