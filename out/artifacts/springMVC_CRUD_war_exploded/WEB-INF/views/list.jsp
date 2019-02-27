<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.min.js"></script>
</head>
<body>

<script>
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("form").attr("action", href).submit();
            return false;
        })
    });
</script>

<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE">
</form>

<c:if test="${empty requestScope.employees}">
    没有信息!
</c:if>

<c:if test="${!empty requestScope.employees}">

    <table>

        <tr>
            <td>ID</td>
            <td>LastName</td>
            <td>Email</td>
            <td>Gender</td>
            <td>Department</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>

        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender==0? "Femele":"Male"}</td>
                <td>${emp.department.departmentName}</td>
                <td><a href="emp/${emp.id}">edit</a></td>
                <td><a class="delete" href="emp/${emp.id}">delete</a></td>
            </tr>
        </c:forEach>

    </table>

</c:if>

<a href="emp">add new info</a>

</body>
</html>
