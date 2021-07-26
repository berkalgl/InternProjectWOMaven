<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title>Person Page</title>
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
            .tg .tg-4eph{background-color:#f9f9f9}
        </style>
    </head>
    <body>
        <c:url var="addPerson" value="/person/add" ></c:url>
        <c:url var="addAddress" value="/person/addAddress" ></c:url>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <br>You are an Administrator.<br/>
        </sec:authorize>

        <h1>Logged in as ${user.userInfo.name}</h1>

        <div>Please choose what you'd like to do</div>
        <table>
            <tr>
                <td> <button >List all Tasks</button> </td>
                <td> <button >Add a new Task</button> </td>
            </tr>
            <tr>
                <td> <button value="List all Employees" onclick="<c:set var='selection' value='listEmp'/>"/> </td>
                <td> <button value="Add a new Employee" onclick="<c:set var='selection' value='addEmp'/>"/> </td>
            </tr>
            <tr>
                <td> <button value="Edit Account" onclick="<c:set var='selection' value='editAcc'/>"/> </td>
            </tr>
        </table>
        <c:if test="selection=='listTask'">
            <a>List Task</a>
        </c:if>

    </body>
</html>