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
        <c:choose>
            <c:when test="${edit}">
                Anzeige bearbeiten
            </c:when>
            <c:otherwise>
                Anzeige aufgeben
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_edit.css"/>" />
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
                <label for="task_owner">Benutzername des Erstellers</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner" value="${task_form.values["task_owner"][0]}" readonly="readonly">
                </div>
                
                <label for="task_owner_vunname">Name des Erstellers</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner_vunname" value="${task_form.values["task_owner_vunname"][0]}" readonly="readonly">
                </div>
                
                <label for="task_owner_anschrift">Straße und Hausnummer</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner_anschrift" value="${task_form.values["task_owner_anschrift"][0]}" readonly="readonly">
                </div>
                
                <label for="task_owner_plz">Postleitzahl</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner_plz" value="${task_form.values["task_owner_plz"][0]}" readonly="readonly">
                </div>

                <label for="task_owner_ort">Name des Erstellers</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner_ort" value="${task_form.values["task_owner_ort"][0]}" readonly="readonly">
                </div>
                
                
                <label for="task_category">Kategorie:</label>
                <div class="side-by-side">
                    <select name="task_category" ${disabled}>
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${task_form.values["task_category"][0] == category.id ? 'selected' : ''}  ${readonlii}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <label for="task_adtype">Art</label>
                <div class="side-by-side">
                    <select name="task_adtype" ${disabled}>
                        <c:forEach items="${adtypes}" var="adtype">
                            <option value="${adtype}" ${task_form.values["task_adtype"][0] == adtype ? 'selected' : ''}>
                            <c:out value="${adtype.label}"/> 
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <label for="task_short_text">Titel</label>
                <div class="side-by-side">
                    <input type="text" name="task_short_text" value="${task_form.values["task_short_text"][0]}"  ${readonlii}>
                </div>
                
                                
                   
                <label for="task_long_text">Beschreibung</label>
                <div class="side-by-side">
                    <textarea name="task_long_text"  ${readonlii}><c:out value="${task_form.values['task_long_text'][0]}" /></textarea>
                </div>
                
                 </div>
                <label for="task_price">Preis in €</label>
                <div class="side-by-side">
                    <input type="text" name="task_price" value="${task_form.values["task_price"][0]}" ${readonlii}>
                </div>
                
                <label for="task_pricetype">Preisart</label>
                <div class="side-by-side">
                    <select name="task_pricetype" ${disabled}>
                        <c:forEach items="${pricetypes}" var="pricetype">
                            <option value="${pricetype}" ${task_form.values["task_pricetype"][0] == pricetype ? 'selected' : ''}>
                            <c:out value="${pricetype.label}"/> 
                            </option>
                        </c:forEach>
                    </select>
                </div>


                <label for="task_due_date">
                    Erstellt am:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_due_date" value="${task_form.values["task_due_date"][0]}" readonly="readonly">
                    <input type="text" name="task_due_time" value="${task_form.values["task_due_time"][0]}" readonly="readonly">
                </div>


                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete">
                            Löschen
                        </button>
                    </c:if>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty task_form.errors}">
                <ul class="errors">
                    <c:forEach items="${task_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>
