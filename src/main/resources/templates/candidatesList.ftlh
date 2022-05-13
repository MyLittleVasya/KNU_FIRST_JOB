<#import "parts/starter.ftlh" as starter>

<@starter.page>
    <div class="row row-cols-5">
        <#if users??>
            <#list users as user, value>
                <div class="profile-block" style="width: 400px;" align="center">
                    <div class="card-body">
                        <#if user.getProfile().PIB??>
                            <h5 class="card-title">${user.getProfile().PIB}</h5>
                            <#else>
                            <h5 class="card-title">Анонімний профіль</h5>
                        </#if>

                        <p class="card-text"><input class="form-control m-1" type="text" value="${user.getFeatureForField()}" data-role="tagsinput" name="features" style="text-align: center" id="skills" disabled/></p>
                        <p class="card-text">Досвід: ${user.getProfile().experience?ifExists} років</p>
                        <p class="card-text" >Відповідність кандидата:
                            <b><#if value gt 100>
                                    ${value-1}
                                <#else>
                                    ${value}
                                </#if>%</b></p>
                        <a href="/profile/${user.id}" class="btn btn-outline-dark m-1">Переглянути профіль кандидата</a>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</@starter.page>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>
    let i = document.getElementById('range'),
        o = document.querySelector('output');

    o.innerHTML = i.value;

    // use 'change' instead to see the difference in response
    i.addEventListener('input', function () {
        o.innerHTML = i.value;
    }, false);
</script>