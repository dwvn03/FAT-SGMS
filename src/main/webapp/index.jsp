<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="utils.auth.AuthUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (AuthUtils.isAuthorized(session)) {
        response.sendRedirect("/dashboard");
        return;
    }
%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/pages/shared/login.jsp" %>
