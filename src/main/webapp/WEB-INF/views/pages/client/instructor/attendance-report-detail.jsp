<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <h1 class="text-3xl text-slate-500 text-center mb-5">Select a student to view attendance report detail</h1>

    <div class="grid grid-flow-row grid-cols-5 auto-cols-max gap-3">
      <c:forEach items="${requestScope.students}" var="student">
          <a class="block p-2 border-solid border-slate-300 border bg-slate-300 text-slate-600 text-center rounded-xl" href="/student/attendance-report/detail?group-id=${param["group-id"]}&student-id=${student.id}">
            ${student.user.name} <br> (${fn:split(student.user.email, "@")[0]})
          </a>
      </c:forEach>
    </div>

  </main>
  <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</div>

</body>
</html>
