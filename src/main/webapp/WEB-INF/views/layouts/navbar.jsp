<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Route" %>
<%@ page import="java.util.Map" %>
<c:set var="page_url" value="${requestScope['jakarta.servlet.forward.request_uri']}"/>
<%
  if (session.getAttribute("role").equals("student")) {
    request.setAttribute("routes", Arrays.asList(
      new Route("/dashboard", "Home"),
      new Route("/student/schedule", "Weekly schedule"),
      Route.builder()
        .location("/student/attendance-report")
        .description("Attendance report")
        .parameters(Map.of("student-id", session.getAttribute("studentId").toString()))
        .build()
    ));
  } else if (session.getAttribute("role").equals("instructor")) {
    request.setAttribute("routes", Arrays.asList(
      new Route("/dashboard", "Home"),
      new Route("/instructor/schedule", "Weekly schedule"),
      Route.builder()
        .location("/instructor/take-attendance")
        .description("Take attendance")
        .disabled(true)
        .build(),
      new Route("/instructor/attendance-report", "Attendance report")
    ));
  }
%>

<nav class="px-4 py-4 md:px-8 md:py-8 basis-5/24 flex flex-col justify-between border-solid border-slate-150 border-r">
  <div>
    <c:forEach items="${routes}" var="route">
      <a href="${route.toUrl()}"
         class="${route.isDisabled() ? 'disabled cursor-not-allowed' : ''} block p-1 pl-3 text-lg ${page_url == route.getLocation() ? "bg-sky-50 rounded-r-md border-solid border-sky-500 border-l-2 text-sky-500" : "border-solid border-slate-300 hover:border-slate-600 border-l-2 text-slate-600 hover:text-slate-900"}">
          ${route.getDescription()}
      </a>
    </c:forEach>
  </div>
  <div class="flex items-center flex-wrap">
    <img class="mr-5 rounded-md w-14 h-14 object-cover"
         src="${sessionScope.getOrDefault("avatar", "https://placehold.co/300x400")}" alt="avatar">

    <div>
      <p class="px-2">${sessionScope.get("name")}</p>
      <p class="px-2">${fn:split(sessionScope.get("email"), "@")[0]}</p>
      <a class="rounded-md inline-block py-1 px-2 text-red-600 hover:text-red-600 bg-red-50 hover:bg-red-200"
         href="/logout">Log out</a>
    </div>
  </div>
</nav>

<script defer>
    document.querySelectorAll('.disabled').forEach(link => {
        link.addEventListener('click', e => {
            e.preventDefault();
        });
    });
</script>