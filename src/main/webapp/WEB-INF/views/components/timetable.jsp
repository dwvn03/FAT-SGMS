<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="isInstructor" value="${sessionScope.role == 'instructor'}"/>
<div class="grid grid-flow-row grid-cols-8 auto-cols-max gap-1">
  <fmt:formatDate value="${from}" pattern="dd" var="day"/>
  <fmt:formatDate value="${from}" pattern="MM" var="month"/>

  <c:set var="dayOfWeeksString" value=" ,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday"/>
  <c:set var="dayOfWeeks" value="${fn:split(dayOfWeeksString, ',')}"/>

  <c:forEach var="date" begin="${day - 1}" end="${day + 6}" varStatus="loop">
    <div class="p-2 bg-slate-100 text-center text-slate-600 font-bold">
      <c:out value="${dayOfWeeks[loop.count - 1]}"/>
      <br>
      <c:if test="${loop.count != 1}">
        <span class="text-xs">(${date}/${month})</span>
      </c:if>
    </div>
  </c:forEach>

  <c:set var="timeSlotsString"
         value="7:30 - 9:50,10:00 - 12:20,12:50 - 15:10,15:20 - 17:40,17:50 - 20:10,20:20 - 22:40"/>
  <c:set var="timeSlots" value="${fn:split(timeSlotsString, ',')}"/>

  <c:forEach items="${requestScope.weeklySchedule}" var="sessions" varStatus="loop">
    <div class="p-2 flex items-center justify-center bg-slate-100 text-center text-slate-600 font-bold">
          <span class="inline-block">
            Slot ${loop.count}
            <br>
            <span class="text-xs">${timeSlots[loop.index]}</span>
          </span>
    </div>

    <c:forEach items="${sessions}" var="session">
      <c:choose>
        <c:when test="${session == null}">
          <div class="flex bg-slate-100 text-center text-slate-600 font-bold items-center justify-center">
            <span class="inline-block"> - </span>
          </div>
        </c:when>

        <c:otherwise>
          <div
            class="${!session.isYetToStart() ? "bg-yellow-100" : isInstructor ? "bg-slate-100" : session.getStatus().get(0).isAttended() ? "bg-green-200" : "bg-red-200"} p-2 text-center text-slate-600 font-bold">
            <a href="#">
                ${session.getGroup().getName()}
            </a>
            <br>
            <span>at ${session.getRoom().getName()}</span>
            <br>
            <c:choose>
              <c:when test="${isInstructor}">
                <c:choose>
                  <c:when test="${!session.isYetToStart()}">
                    <span class="text-yellow-500">(not yet)</span>
                  </c:when>

                  <c:otherwise>
                    <a class="underline text-md text-rose-600"
                       href="/instructor/take-attendance?session_id=${session.getId()}">
                      Take Attendance
                    </a>
                  </c:otherwise>
                </c:choose>
              </c:when>

              <c:otherwise>
                <c:choose>
                  <c:when test="${!session.isYetToStart()}">
                    <span class="text-yellow-500">(not yet)</span>
                  </c:when>

                  <c:when test="${session.getStatus().get(0).isAttended()}">
                    <span class="text-green-600">(attended)</span>
                  </c:when>

                  <c:otherwise>
                    <span class="text-red-500">(absent)</span>
                  </c:otherwise>
                </c:choose>
              </c:otherwise>
            </c:choose>

          </div>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </c:forEach>
</div>
