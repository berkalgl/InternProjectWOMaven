<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
            .tg .tg-4eph{background-color:#f9f9f9}
            .tg .edit {background-color:  #666666 !important;}
        </style>
    </head>
    <body>

        <c:url var="register" value="/tryregister" ></c:url>
        <c:url var="addAddress" value="/addAddress" ></c:url>
        <c:url var="updateAddress" value="/updateAddress" ></c:url>
        <c:url var="cancelUpdate" value="/cancelUpdate" ></c:url>

        <form:form action="" modelAttribute="user">
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
                    <td> <form:label path="userInfo.name"> <spring:message text="Name: "/> </form:label> </td>
                    <td> <form:input path="userInfo.name" /> </td> 
                </tr>
                <tr>
                    <td> <form:label path="userInfo.surName"> <spring:message text="Surname: "/> </form:label> </td>
                    <td> <form:input path="userInfo.surName" /> </td> 
                </tr>
                <c:forEach items="${user.userInfo.addresses}" varStatus="i">
                    <c:choose>
                        <c:when test="${i.index==(currAddingIndex-isEdit)}">
                            <table>
                            </c:when>
                            <c:otherwise>
                                <table hidden>
                                </c:otherwise>
                            </c:choose>
                            <tr>
                                <td> <form:label path="userInfo.addresses[${i.index}].street"> <spring:message text="Street"/> </form:label> </td>
                                <td> <form:input path="userInfo.addresses[${i.index}].street" /> </td>
                            </tr>
                            <tr>
                                <td> <form:label path="userInfo.addresses[${i.index}].state"> <spring:message text="State"/> </form:label> </td>
                                <td> <form:input path="userInfo.addresses[${i.index}].state" /> </td>
                            </tr>
                            <tr>
                                <td> <form:label path="userInfo.addresses[${i.index}].country"> <spring:message text="Country"/> </form:label> </td>
                                <td> <form:input path="userInfo.addresses[${i.index}].country" /> </td>
                            </tr>
                            <tr>
                                <td> <form:label path="userInfo.addresses[${i.index}].postCode"> <spring:message text="Post Code"/> </form:label> </td>
                                <td> <form:input path="userInfo.addresses[${i.index}].postCode" /> </td>
                            </tr>
                        </table>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isEdit==1}">
                            <tr>
                                <td> <input type="submit" value="<spring:message text="Apply Changes"/>" 
                                            onclick="document.getElementById('user').setAttribute('action', '${updateAddress}/${currEditingIndex}')"/> </td>
                            </tr>
                            <tr>
                                <td colspan="2"> <input type="submit" value="<spring:message text="Cancel"/>" 
                                                        onclick="document.getElementById('user').setAttribute('action', '${cancelUpdate}/${currEditingIndex}')"/> </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td> <input type="submit" value="<spring:message text="Add Address"/>" 
                                            onclick="document.getElementById('user').setAttribute('action', '${addAddress}')"/> </td>
                            </tr>
                            <tr>
                                <td colspan="2"> <input type="submit" value="<spring:message text="Register"/>" 
                                                        onclick="document.getElementById('user').setAttribute('action', '${register}')"/> </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>	

                <c:if test="${(!empty user.userInfo.addresses)&&(fn:length(user.userInfo.addresses)!=1)}">
                    <h3>Users' Address List</h3>
                    <table class="tg">
                        <tr>
                            <th width="80">Street</th>
                            <th width="120">State</th>
                            <th width="120">Country</th>
                            <th width="120">Post Code</th>
                            <th width="60">Edit</th>
                            <th width="60">Delete</th>
                        </tr>
                        <c:forEach items="${user.userInfo.addresses}" var="address" varStatus="i">
                            <c:if test="${i.index != currAddingIndex&&i.index != currAddingIndex-isEdit}">
                                <c:choose>
                                    <c:when test="${i.index == currEditingIndex}">
                                        <tr class="edit">
                                        </c:when>
                                        <c:otherwise>
                                        <tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${address.street}</td>
                                    <td>${address.state}</td>
                                    <td>${address.country}</td>
                                    <td>${address.postCode}</td>
                                    <c:choose>
                                        <c:when test="${isEdit==1}">
                                            <td><input type="submit" value="<spring:message text="Edit Address"/>" 
                                                       onclick="document.getElementById('user').setAttribute('action', 'editAddress/${i.index}')"
                                                       disabled/></td>
                                            <td><input type="submit" value="<spring:message text="Remove Address"/>" 
                                                       onclick="document.getElementById('user').setAttribute('action', 'removeAddress/${i.index}')"
                                                       disabled/></td>
                                            </c:when>
                                            <c:otherwise>
                                            <td><input type="submit" value="<spring:message text="Edit Address"/>" 
                                                       onclick="document.getElementById('user').setAttribute('action', 'editAddress/${i.index}')"/></td>
                                            <td><input type="submit" value="<spring:message text="Remove Address"/>" 
                                                       onclick="document.getElementById('user').setAttribute('action', 'removeAddress/${i.index}')"/></td>
                                            </c:otherwise>
                                        </c:choose>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
            </form:form>
    </body>
</html>
