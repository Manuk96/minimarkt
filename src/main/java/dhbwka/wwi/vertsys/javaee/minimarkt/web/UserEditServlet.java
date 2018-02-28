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
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.Task;
import dhbwka.wwi.vertsys.javaee.minimarkt.jpa.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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

@WebServlet(urlPatterns = {"/useredit/"})
public class UserEditServlet extends HttpServlet{
    
    @EJB 
    ValidationBean validationBean;
    
    @EJB
    UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    HttpSession session = request.getSession();

    User user = this.getRequestedUser(request);
        request.setAttribute("edit", user.getId() != 0);
                                
        if (session.getAttribute("user_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("user_form", this.createUserForm(user));
        }

        // Anfrage an die JSP weiterleiten
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
        
        user.set
    }
    
    private FormValues createUserForm(Task task) {
        Map<String, String[]> values = new HashMap<>();

        values.put("task_owner", new String[]{
            task.getOwner().getUsername()
        });

        values.put("edit_vunname_text", new String[]{
            task.getOwner().getUsername()
        });
        
        values.put("edit_anschrift_text", new String[]{
            task.getOwner().getUsername()
        });
        
        values.put("edit_plz_text", new String[]{
            task.getOwner().getUsername()
        });

        values.put("edit_ort_text", new String[]{
            task.getOwner().getUsername()
        });
        
        values.put("edit_telefon_text", new String[]{
            task.getOwner().getUsername()
        });
        
        values.put("edit_email_text", new String[]{
            task.getOwner().getUsername()
        });
        
        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }
    
    private User getRequestedUser(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        User user = userBean.getCurrentUser();
        user.setUsername(this.userBean.getCurrentUser());
        task.setDueDate(new Date(System.currentTimeMillis()));
        task.setDueTime(new Time(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String taskId = request.getPathInfo();

        if (taskId == null) {
            taskId = "";
        }

        taskId = taskId.substring(1);

        if (taskId.endsWith("/")) {
            taskId = taskId.substring(0, taskId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            task = this.taskBean.findById(Long.parseLong(taskId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return task;
    }

}
