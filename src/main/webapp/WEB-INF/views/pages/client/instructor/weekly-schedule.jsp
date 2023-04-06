<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
  <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
  <title>Weekly schedule - FAT-SGMS</title>
</head>
<body class="flex h-screen">
<jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>

<div class="flex-grow flex flex-col">
  <main class="flex-grow container p-4 md:p-8 mx-auto">
    <h1 class="mb-5 text-4xl text-slate-600 text-center">Weekly schedule</h1>

    <jsp:include page="/WEB-INF/views/components/date-select.jsp"/>
    <jsp:include page="/WEB-INF/views/components/timetable.jsp"/>
  </main>

  <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</div>

<script src="/assets/js/weekly-schedule.js" defer></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
<script defer>
  <c:if test="${param.succeeded != null && param.succeeded}">
    Toastify({
      text: "Attendance successfully recorded!",
      duration: 3000,
      close: true,
      gravity: "top",
      position: "right",
      className: "text-green-100 bg-gradient-to-r from-green-600 to-green-700",
      stopOnFocus: true,
    }).showToast();
  </c:if>

  <c:if test="${param.succeeded != null && !param.succeeded}">
    Toastify({
      text: "Attendance failed to record",
      duration: 3000,
      close: true,
      gravity: "top",
      position: "right",
      className: "text-red-100 bg-gradient-to-r from-red-600 to-red-700",
      stopOnFocus: true,
    }).showToast();
  </c:if>
</script>
</body>
</html>
