<#import "parts/starter.ftlh" as starter>
<#import "parts/login.ftlh" as reg>

<@starter.page>
    <#if message??>
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <svg class=" rounded mr-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img">
                    <rect fill="#007aff" width="100%" height="100%" /></svg>
                <strong class="mr-auto">Bootstrap</strong>
                <small class="text-muted">11 mins ago</small>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                ${message?if_exists}
            </div>
        </div>
    </#if>
    <@reg.login "/registration" true/>
</@starter.page>