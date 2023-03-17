<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="page_url" value="${requestScope['jakarta.servlet.forward.request_uri']}"/>
<nav class="px-4 py-4 basis-3/12 flex flex-col justify-between border-solid border-gray-300 border-r">
    <ul>
        <li class="pl-2 text-lg ${page_url == "/dashboard" ? "border-solid border-sky-500 border-l-2 text-sky-500" : "border-solid border-slate-300 hover:border-slate-600 border-l-2 text-slate-600 hover:text-slate-900"}">
            <a href="/dashboard">Home</a>
        </li>
        <li class="pl-2 text-lg ${page_url == "/#" ? "border-solid border-sky-500 border-l-2 text-sky-500" : "border-solid border-slate-300 hover:border-slate-600 border-l-2 text-slate-600 hover:text-slate-900"}">
            <a href="#">Weekly schedule</a>
        </li>
        <li class="pl-2 text-lg ${page_url == "/#" ? "border-solid border-sky-500 border-l-2 text-sky-500" : "border-solid border-slate-300 hover:border-slate-600 border-l-2 text-slate-600 hover:text-slate-900"}">
            <a href="#">Attendance report</a>
        </li>
    </ul>

    <div class="flex items-center flex-wrap">
        <img class="mr-5 rounded-full w-10 h-10 object-cover" src="${sessionScope.getOrDefault("avatar", "https://placehold.co/300x400")}" alt="avatar">

        <div>
            <p>${sessionScope.get("name")}</p>
            <p>${fn:split(sessionScope.get("email"), "@")[0]}</p>
            <a class="text-red-600" href="/logout">Log out</a>
        </div>
    </div>
</nav>