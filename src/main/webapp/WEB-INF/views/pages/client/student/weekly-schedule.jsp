<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
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
</body>
</html>
