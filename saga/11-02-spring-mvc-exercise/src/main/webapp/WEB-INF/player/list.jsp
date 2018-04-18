<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div id="page">
    <div class="content">
        <h2>
           Players list
        </h2>

        <div class="users">
            <table>
                <thead>
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        Username
                    </td>
                    <td>
                        First Name
                    </td>
                    <td>
                        Last Name
                    </td>
                </tr>
                </thead>
                <c:forEach var="user" items="${players}">
                    <tr>
                        <td>
                            <spring:url var="showUrl" value="{id}">
                                <spring:param name="id" value="${user.id}"/>
                            </spring:url>
                            <a href="${showUrl}">${user.id}</a>
                        </td>
                        <td>
                                ${user.credentials.username}
                        </td>
                        <td>
                                ${user.firstName}
                        </td>
                        <td>
                                ${user.lastName}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="footer">
    </div>
</div>
</body>
</html>