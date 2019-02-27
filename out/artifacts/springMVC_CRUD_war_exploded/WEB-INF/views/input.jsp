<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="${path}/emp" method="post" modelAttribute="employee">

    <form:errors path="*"/>

    <c:if test="${employee.id == null}">
        LastName:<form:input path="lastName"/>
    </c:if>

    <c:if test="${employee.id != null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>

    <br>
    email:<form:input path="email"/>
    <br>

    <%
        Map<String, String> genders = new HashMap<String, String>();
        genders.put("1", "Male");
        genders.put("0", "Female");
        request.setAttribute("genders", genders);
    %>


    birth: <form:input path="birth"/>

    gender:<form:radiobuttons path="gender" items="${genders }"/>
    <br>
    department:<form:select path="department.id" items="${departments}"
                            itemLabel="departmentName"
                            itemValue="id"/>
    <input type="submit" value="submit"/>

</form:form>

</body>
</html>
