/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.minimarkt.web;

import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/user_edit/"})
public class UserEditServlet extends HttpServlet{
    
    @EJB 
    private ValidationBean validationBean;
    
    @EJB
    private UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    HttpSession session = request.getSession();

    User user = this.userBean.getCurrentUser();
    
    
    if (session.getAttribute("user_form") == null) {
        request.setAttribute("user_form", this.createUserForm(user));
    }
    
    request.getRequestDispatcher("/WEB-INF/app/user_edit.jsp").forward(request, response);

    session.removeAttribute("user_form");
    }
            
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveUser(request, response);
                break;
        }
    }
    
    public void saveUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<String> errors = new ArrayList<>();

        String vunname = request.getParameter("edit_vunname_text");
        String anschrift = request.getParameter("edit_anschrift_text");
        String plz = request.getParameter("edit_plz_text");
        String ort = request.getParameter("edit_ort_text");
        String telefon = request.getParameter("edit_telefon_text");
        String email = request.getParameter("edit_email_text");

        User user = this.userBean.getCurrentUser();
        
        user.setVunname(vunname);
        user.setAnschrift(anschrift);
        user.setPlz(plz);
        user.setOrt(ort);
        user.setTelefon(telefon);
        user.setEmail(email);
        
        this.validationBean.validate(user, errors);
        
        if (errors.isEmpty()) {
            this.userBean.update(user);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("user_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
        
        
        
      
    }
    
    private FormValues createUserForm(User user) {
        Map<String, String[]> values = new HashMap<>();

        values.put("task_owner", new String[]{
            user.getUsername()
        });

        values.put("edit_vunname_text", new String[]{
            user.getVunname()
        });
        
        values.put("edit_anschrift_text", new String[]{
            user.getAnschrift()
        });
        
        values.put("edit_plz_text", new String[]{
            user.getPlz()
        });

        values.put("edit_ort_text", new String[]{
            user.getOrt()
        });
        
        values.put("edit_telefon_text", new String[]{
            user.getTelefon()
        });
        
        values.put("edit_email_text", new String[]{
            user.getEmail()
        });
        
        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }
    

}
