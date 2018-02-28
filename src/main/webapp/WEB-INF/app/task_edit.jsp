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
                Aufgabe bearbeiten
            </c:when>
            <c:otherwise>
                Aufgabe anlegen
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
                <label for="task_owner">Ersteller</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner" value="${task_form.values["task_owner"][0]}" readonly="readonly">
                </div>

                <label for="task_category">Kategorie:</label>
                <div class="side-by-side">
                    <select name="task_category">
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${task_form.values["task_category"][0] == category.id ? 'selected' : ''}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <label for="task_type">Art</label>
                <div class="side-by-side">
                    <select name="task_type">
                        <option value="">Keine Art</option>
                        <option value="sell">Biete an</option>
                        <option value="buy">suche</option>
                    </select>
                </div>
                <label for="task_title">Titel</label>
                <div class="side-by-side">
                    <input type="text" name="task_title" value="${task_form.values["task_title"][0]}" ">
                </div>
                
                                
                   
                <label for="task_beschreibung">Beschreibung</label>
                <div class="side-by-side">
                    <textarea name="task_beschreibung"><c:out value="${task_form.values['task_beschreibung'][0]}"/></textarea>
                </div>
                
                 </div>
                <label for="task_price">Preis in €</label>
                <div class="side-by-side">
                    <input type="text" name="task_price" value="${task_form.values["task_price"][0]}" ">
                </div>
                
                                </div>
                <label for="task_preis">Preisart</label>
                <div class="side-by-side">
                    <select name="task_preis">
                        <option value="">Keine Angabe</option>
                        <option value="vb">Verhandlungsbasis</option>
                        <option value="fp">Festpreis</option>
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
