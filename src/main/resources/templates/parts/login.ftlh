<#macro login path isRegisterForm>
        <form action="${path}" method="post">
                <label class="col-form-label">User Name :</label>
                <div class="col-sm-6">
                    <input type="text" name="username" class="form-control" placeholder="User name" />
                </div>
                <label class="col-sm-2 col-form-label" align="center">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password" class="form-control" placeholder="Password" />
                </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div align="center">
            <button class="btn btn-outline-dark m-1" type="submit">
                <#if isRegisterForm>
                    Створити
                    <#else>Log In
                </#if></button>
                <div align="center">
                    <#if !isRegisterForm>
                        <a href="/registration">Registration</a>
                    </#if>
                </div>
            </div>
        </form>
</#macro>