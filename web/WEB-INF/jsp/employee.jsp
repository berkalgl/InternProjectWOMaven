<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
        <h1>
            Add a Person
        </h1>
        <c:url var="addPerson" value="/person/add" ></c:url>
        <c:url var="addAddress" value="/person/addAddress" ></c:url>
        <c:set var="currEditingIndex" scope="session" value="-1"/>

        <form:form action="" modelAttribute="person">
            <table>
                <a>${currEditingIndex}
                <tr>
                    <td> <form:label path="name"> <spring:message text="Name"/> </form:label> </td>
                    <td> <form:input path="name" /> </td> 
                </tr>
                <c:forEach items="${person.addresses}" varStatus="i">
                    <c:choose>
                        <c:when test="${i.index==currAddingIndex}">
                            <table>
                            </c:when>
                            <c:otherwise>
                                <table hidden>
                                </c:otherwise>
                            </c:choose>
                            <tr class="addressFields${i.index}">
                                <td> <form:label path="addresses[${i.index}].street"> <spring:message text="Street"/> </form:label> </td>
                                <td> <form:input path="addresses[${i.index}].street" /> </td>
                            </tr>
                            <tr class="addressFields">
                                <td> <form:label path="addresses[${i.index}].state"> <spring:message text="State"/> </form:label> </td>
                                <td> <form:input path="addresses[${i.index}].state" /> </td>
                            </tr>
                            <tr class="addressFields">
                                <td> <form:label path="addresses[${i.index}].country"> <spring:message text="Country"/> </form:label> </td>
                                <td> <form:input path="addresses[${i.index}].country" /> </td>
                            </tr>
                            <tr class="addressFields">
                                <td> <form:label path="addresses[${i.index}].postCode"> <spring:message text="Post Code"/> </form:label> </td>
                                <td> <form:input path="addresses[${i.index}].postCode" /> </td>
                            </tr>
                        </table>
                    </c:forEach>
                    <tr>
                        <td> <input type="submit" value="<spring:message text="Add Address"/>" 
                                    onclick="document.getElementById('person').setAttribute('action', '${addAddress}')"/> </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:if test="${currEditingIndex != -1}">
                                <input type="submit" value="<spring:message text="Edit Person"/>" />
                            </c:if>
                            <c:if test="${currEditingIndex == -1}">
                                <input type="submit" value="<spring:message text="Add Person"/>" 
                                       onclick="document.getElementById('person').setAttribute('action', '${addPerson}')"/>
                            </c:if>
                        </td>
                    </tr>
                </table>	
            </form:form>

            <c:if test="${(!empty person.addresses)&&(fn:length(person.addresses)!=1)}">
                <h3>Persons' Address List</h3>
                <table class="tg">
                    <tr>
                        <th width="80">Street</th>
                        <th width="120">State</th>
                        <th width="120">Country</th>
                        <th width="60">Edit</th>
                        <th width="60">Delete</th>
                    </tr>
                    <c:forEach items="${person.addresses}" var="address" varStatus="i">
                        <c:if test="${i.index != currAddingIndex}">
                            <tr>
                                <td>${address.street}</td>
                                <td>${address.state}</td>
                                <td>${address.country}</td>
                                <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                                <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
            <h3>Persons List</h3>
            <c:if test="${!empty listPersons}">
                <table class="tg">
                    <tr>
                        <th width="80">Person ID</th>
                        <th width="120">Person Name</th>
                        <th width="120">Person Address Street</th>
                        <th width="120">Person Address State</th>
                        <th width="120">Person Address Country</th>
                        <th width="120">Person Address PostCode</th>
                        <th width="60">Edit</th>
                        <th width="60">Delete</th>
                    </tr>
                    <c:forEach items="${listPersons}" var="person">
                        <tr>
                            <c:if test="${empty person.addresses}">
                                <td>${person.id}</td>
                                <td>${person.name}</td>
                                <td>Not Provided</td>
                                <td>Not Provided</td>
                                <td>Not Provided</td>
                                <td>Not Provided</td>
                                <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                                <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
                            </tr>
                        </c:if>
                        <c:forEach items="${person.addresses}" var="address">
                            <tr>
                                <td>${person.id}</td>
                                <td>${person.name}</td>
                                <td>${address.street}</td>
                                <td>${address.state}</td>
                                <td>${address.country}</td>
                                <td>${address.postCode}</td>
                                <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                                <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </c:if>
    </body>
</html>
