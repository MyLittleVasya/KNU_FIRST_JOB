<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">FirstJob</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/createVacancy">Create vacancy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancyList">Vacancies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/viewAuthorVacancies">My vacancies</a>
                </li>
            </ul>
            <#if known>
                <form action="/profile/${securityId}" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-outline-dark m-1" type="submit">${name} - профіль</button>
                </form>
            </#if>
            <form action="/login" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-outline-dark m-1" type="submit">Log in</button>
            </form>
        </div>
    </div>
</nav>