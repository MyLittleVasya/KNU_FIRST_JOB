<#import "parts/starter.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="profile-block" align="center">
        <@l.login "/login" false/>
    </div>
</@c.page>