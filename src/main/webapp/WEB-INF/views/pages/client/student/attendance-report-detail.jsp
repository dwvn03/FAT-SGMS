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
    <h1 class="mb-5 text-4xl text-slate-600 text-center">
      Attendance report for student ${requestScope.statuses[0].student.user.name}
      (${fn:split(requestScope.statuses[0].student.user.email, "@")[0]})<br>
      in group ${requestScope.statuses[0].session.group.name}
    </h1>

    <p class="mb-5 text-2xl text-slate-600 text-center">
      Absent:
      <fmt:formatNumber value="${requestScope.absentPercentage}" type="number" maxFractionDigits="2"></fmt:formatNumber>%
      (${requestScope.numberOfAbsent} absents
      on ${requestScope.statuses.size()} total)
    </p>

    <c:if test="${requestScope.absentPercentage > 20}">
      <p class="mb-5 text-2xl text-red-500 text-center">
        You are not eligible for the final exam because you have more than 20% absents.
      </p>
    </c:if>

    <div class="grid grid-flow-row grid-cols-5 auto-cols-max gap-y-1">
      <c:set var="tableHeaderString" value="Index,Date,Slot,Lecturer,Status"/>
      <c:set var="tableHeaders" value="${fn:split(tableHeaderString, ',')}"/>

      <c:forEach var="head" items="${tableHeaders}" varStatus="loop">
      <div class="p-2 bg-slate-100 text-center text-slate-600 font-bold">
          ${head}
      </div>
      </c:forEach>

      <c:forEach items="${requestScope.statuses}" var="status" varStatus="loop">
        <c:set var="bg"
               value="${!status.session.isYetToStart() ? 'bg-slate-200' : status.attended ? 'bg-green-200' : 'bg-red-200'}"/>
        <div class="p-1 text-center ${bg} flex justify-center items-center">${loop.count}</div>
        <div class="p-1 text-center ${bg} flex justify-center items-center">
          <fmt:formatDate value="${status.session.date}" pattern="DD/MM/yyyy"/>
        </div>
        <div class="p-1 text-center ${bg} flex justify-center items-center">
          <p>
              ${status.session.timeSlot.id}
          </p>
        </div>
        <div class="p-1 text-center ${bg} flex justify-center items-center">
          <p>
              ${status.session.instructor.name}
          </p>
        </div>

        <div class="p-1 text-center ${bg} flex justify-center items-center">
          <c:choose>
            <c:when test="${!status.session.isYetToStart()}">
              <p class="text-slate-600">Future</p>
            </c:when>

            <c:when test="${status.attended}">
              <p class="text-green-600">Attended</p>
            </c:when>

            <c:otherwise>
              <p class="text-red-600">Absent</p>
            </c:otherwise>
          </c:choose>
        </div>
      </c:forEach>
  </main>
  <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</div>

</body>
</html>
