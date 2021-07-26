<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style type="text/css">
            #login-box {
                width: 300px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
            }
            .error {
                padding: 2px;
                margin-bottom: 2px;
                border: 1px solid transparent;
                border-radius: 2px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:url var="trylogin" value="/j_spring_security_check" ></c:url>
        <c:url var="register" value="/register" ></c:url>
            <div id="login-box">
            <form:form action="" modelAttribute="user" method="POST">
                <table>
                    <tr>
                        <td> <form:label path="username"> <spring:message text="Username: "/> </form:label> </td>
                        <td> <form:input path="username" /> </td> 
                    </tr>
                    <tr>
                        <td> <form:label path="password"> <spring:message text="Password: "/> </form:label> </td>
                        <td> <form:password path="password" /> </td> 
                    </tr>
                    <tr>
                        <td> <input type="submit" value="<spring:message text="Login"/>"
                                    onclick="document.getElementById('user').setAttribute('action', '${trylogin}')"/> </td>
                        <td> <input type="submit" value="<spring:message text="Register"/>"
                                    onclick="document.getElementById('user').setAttribute('action', '${register}')"/> </td>
                    </tr>
                </table>
            </form:form>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </div>
    </body>
</html>
