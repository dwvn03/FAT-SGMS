<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
  <title>Attendance report - FAT-SGMS</title>
</head>
<body class="flex h-screen">
<jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>

<div class="flex-grow flex flex-col">
  <main class="flex-grow container p-4 md:p-8 mx-auto">
    <h1 class="mb-5 text-4xl text-slate-600 text-center">Attendance report</h1>

    <p class="mb-5 text-2xl text-slate-500 text-center">Select a group to view attendance report</p>

    <div class="flex flex-col items-center">
      <c:forEach items="${requestScope.groups}" var="group">
        <a class="block p-2 border-solid border-slate-200 border bg-slate-200 hover:bg-slate-300 rounded-xl" href="/instructor/attendance-report/detail?group-id=${group.id}">${group.name}</a>
      </c:forEach>
    </div>
  </main>

  <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</div>

</body>
</html>
