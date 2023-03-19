<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
    <title>FAT-SGMS</title>
</head>
<body class="flex h-screen">
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp" />

    <div class="flex-grow flex flex-col">
        <main class="flex-grow container p-4 md:p-8">
            <h1 class="mb-5 text-4xl text-slate-600 text-center">Dashboard</h1>
        </main>

        <%@ include file="/WEB-INF/views/layouts/footer.html" %>
    </div>
</body>
</html>
