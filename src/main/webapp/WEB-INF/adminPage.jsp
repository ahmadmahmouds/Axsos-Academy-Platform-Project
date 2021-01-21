<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
</head>
<body>
<h1>Welcome to the Admin Page <c:out value="${currentUser.username}"></c:out></h1>

<p><form:errors path="user.*"/></p>
<form:form method="POST" action="/admin" modelAttribute="user">

    <p>
        <form:label path="username">Username:</form:label>
        <form:input path="username"/>
    </p>
    <p>
        <form:label path="name">Name:</form:label>
        <form:input path="name"/>
    </p>
    <p>
        <form:input  type="hidden" value="123456789" path="password"/>
    </p>
    <input type="submit" value="Register!"/>
</form:form>

<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>


</body>
</html>