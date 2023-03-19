<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form action="/student/schedule" method="post">
  <div>
    <fmt:formatDate value="${requestScope.from}" pattern="yyyy" var="fromYear"/>
    <span>Year</span>
    <select class="rounded-xl" name="year">
      <c:forEach var="year" begin="2019" end="2023">
        <option value="${year}" ${fromYear == year ? "selected" : ""} >${year}</option>
      </c:forEach>
    </select>

    <fmt:formatDate value="${requestScope.from}" pattern="dd/MM" var="fromDate"/>
    <span>Date</span>
    <select class="rounded-xl" name="fromDate">
      <c:forEach var="monday" items="${requestScope.mondaysAndSundays.get(requestScope.fromYearIndex).get(0)}"
                 varStatus="loop">
        <option value="${monday}" ${fromDate == monday ? "selected" : ""} >${monday}
          to ${requestScope.mondaysAndSundays.get(requestScope.fromYearIndex).get(1).get(loop.index)}</option>
      </c:forEach>
    </select>
  </div>
</form>