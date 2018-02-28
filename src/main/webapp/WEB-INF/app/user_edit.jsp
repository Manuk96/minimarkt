<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
                Benutzerdaten bearbeiten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/user_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>
                <label for="task_owner">Eigentümer:</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner" value="${user_form.values["task_owner"][0]}" readonly="readonly">
                </div>

                <label for="edit_vunname">Name:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_vunname_text" value="${user_form.values["edit_vunname_text"][0]}">
                </div>
                
                <label for="edit_anschrift">Anschrift:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_anschrift_text" value="${user_form.values["edit_anschrift_text"][0]}">
                </div>
                
                <label for="edit_plz">Postleitzahl:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_plz_text" value="${user_form.values["edit_plz_text"][0]}">
                </div>
                
                <label for="edit_ort">Ort:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_ort_text" value="${user_form.values["edit_ort_text"][0]}">
                </div>
                
                <label for="edit_telefon">Telefon:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_telefon_text" value="${user_form.values["edit_telefon_text"][0]}">
                </div>
                
                <label for="edit_email">E-Mail:</label>
                <div class="side-by-side">
                    <input type="text" name="edit_email_text" value="${user_form.values["edit_email_text"][0]}">
                </div>
               

                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty user_form.errors}">
                <ul class="errors">
                    <c:forEach items="${user_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>