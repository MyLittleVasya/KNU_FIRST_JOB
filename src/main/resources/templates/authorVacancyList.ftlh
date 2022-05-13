<#import "parts/starter.ftlh" as starter>

<@starter.page>
    <div class="row row-cols-5">
        <#if vacancies??>
            <#list vacancies as vacancy>
                <div class="profile-block" style="width: 400px;" align="center">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <p class="card-text"><input class="form-control m-1" type="text" value="${vacancy.getFeatureForField()}" data-role="tagsinput" name="features" style="text-align: center" id="skills" disabled/></p>
                        <p class="card-text">Досвід: ${vacancy.experience} років</p>
                        <p class="card-text" style="font-size: 35px">${vacancy.salary}$</p>
                        <a href="/vacancy/${vacancy.id}" class="btn btn-outline-dark m-1">Переглянути вакансію</a>
                        <a href="/candidates/${vacancy.id}" class="btn btn-outline-dark m-1">Переглянути кандидатів</a>
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