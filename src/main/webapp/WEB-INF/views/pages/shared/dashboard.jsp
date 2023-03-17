<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
    <title>FAT-SGMS</title>
</head>
<body class="flex flex-col h-screen">
    <div class="flex-grow flex mx-2 md:mx-8">
        <jsp:include page="/WEB-INF/views/layouts/navbar.jsp" />
        <main class="flex-grow container">
            <h1>Dashboard</h1>

        </main>
    </div>
    <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</body>
</html>
