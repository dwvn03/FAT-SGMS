<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <%@ include file="/WEB-INF/views/layouts/common-head.html" %>
  <title>Take attendance - FAT-SGMS</title>
</head>
<body class="flex h-screen">
<jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>

<div class="flex-grow flex flex-col">
  <main class="flex-grow container p-4 md:p-8 mx-auto">
    <h1 class="mb-5 text-4xl text-slate-600 text-center">Take attendance</h1>

    <div class="flex gap-3">
      <button class="border border-solid border-slate-200 rounded-md p-2 mb-5 bg-slate-200 hover:bg-slate-300" onclick="toggleAvatar()">Toggle avatar</button>
      <button class="border border-solid border-green-400 rounded-md p-2 mb-5 bg-green-200 hover:bg-green-300" onclick="toggleAttendance(true)">Mark all as present</button>
      <button class="border border-solid border-red-400 rounded-md p-2 mb-5 bg-red-200 hover:bg-red-300" onclick="toggleAttendance(false)">Mark all as absent</button>
    </div>

    <form action="/instructor/take-attendance" method="post">
      <input type="hidden" name="session_id" value="${requestScope.session_id}">
      <input type="hidden" name="number_of_students" value="${requestScope.statuses.size()}">
      <div class="grid grid-flow-row grid-cols-5 auto-cols-max gap-y-1">
        <c:set var="tableHeaderString" value="Index,Avatar,Member Code,Name,Status"/>
        <c:set var="tableHeaders" value="${fn:split(tableHeaderString, ',')}"/>

        <c:forEach var="head" items="${tableHeaders}" varStatus="loop">
          <div class="p-2 bg-slate-100 text-center text-slate-600 font-bold">
            ${head}
          </div>
        </c:forEach>

        <c:forEach items="${requestScope.statuses}" var="status" varStatus="loop">
          <div class="text-center bg-slate-100 flex justify-center items-center">${loop.count}</div>
          <div class="text-center bg-slate-100 flex justify-center items-center">
            <img class="w-40 h-40 rounded-md" src="${status.student.user.avatar}" alt="${status.student.user.name}">
          </div>
          <div class="text-center bg-slate-100 flex justify-center items-center">
            <p>
              ${fn:split(status.student.user.email, "@")[0]}
            </p>
          </div>
          <div class="text-center bg-slate-100 flex justify-center items-center">
            <p>
              ${status.student.user.name}
            </p>
          </div>
          <div class="text-center bg-slate-100 flex justify-center items-center">
            <label class="relative inline-block w-14 h-7" for="att_${status.student.id}">
              <input type="hidden" name="student_id" value="${status.student.id}">
              <input type="hidden" id="att_${status.student.id}_hidden" name="attended" value="0" >
              <input class="invisible peer" type="checkbox" id="att_${status.student.id}" name="attended" value="1" ${status.attended == true ? "checked" : ""}  />
              <span class="peer-checked:bg-green-500 peer-checked:before:translate-x-6 absolute cursor-pointer inset-0 bg-red-500 transition-[400ms] rounded-full before:rounded-full before:absolute before:w-6 before:h-6 before:left-1 before:bottom-0.5 before:bg-white before:transition-[400ms]"></span>
            </label>

          </div>
        </c:forEach>
      </div>

      <div class="flex justify-center mt-5">
        <button type="submit" class="border border-solid border-slate-200 rounded-md p-2 mb-5 bg-slate-200 hover:bg-slate-300">Submit</button>
      </div>
    </form>
  </main>

  <%@ include file="/WEB-INF/views/layouts/footer.html" %>
</div>
<script defer>
    const form = document.querySelector('form');
    const avatars = document.querySelectorAll('img');
    const checkboxes = document.querySelectorAll('.peer');

    form.addEventListener('submit', () => {
        <c:forEach items="${requestScope.statuses}" var="status">
          if(document.querySelector("#att_${status.student.id}").checked) {
              document.querySelector("#att_${status.student.id}_hidden").disabled = true;
          }
        </c:forEach>
    })

    function toggleAvatar() {
        avatars.forEach(avatar => {
            avatar.classList.toggle('hidden');
        })
    }

    function toggleAttendance(checked) {
        checkboxes.forEach(checkbox => {
            checkbox.checked = checked;
        })
    }
</script>
</body>
</html>
