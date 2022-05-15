<#import "parts/starter.ftlh" as starter>

<@starter.page>
    <#if message??>
        <div class="alert alert-primary d-flex align-items-center" role="alert">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
            <div>
                ${message}
            </div>
        </div>
    </#if>
    <form action="/settings" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="profile-body" style="margin: 0% 25%" align="center">
            <h4>Налаштування користувача</h4>
            <label for="email">Пошта</label>
            <input class="form-control m-1 w-50" name="email" value="${user.profile.email?ifExists}" type="email" placeholder="пошта" style="text-align: center;" id="email" required/>
            <label for="password">Пароль</label>
            <input class="form-control m-1 w-50" name="password" type="password" placeholder="Новий пароль" style="text-align: center;" id="password"/>
            <h5>Якщо не бажаєте змінювати пароль - залишіть поле пустим</h5>
            <button class="btn btn-outline-dark m-1" type="submit">Оновити налаштування</button>
        </div>
    </form>
</@starter.page>