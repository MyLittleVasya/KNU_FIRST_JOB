<#import "parts/starter.ftlh" as starter>
<#include "parts/security.ftlh">

<@starter.page>
    <div class="profile-body mb-4 mt-1" align="center">
        <form action="/createVacancy" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="author_id" value="${securityId}">
            <div class="col-sm-6 my-1 mx-2 profile-block">
                <label for="name">Назва вакансії</label>
                <input class="form-control m-1 w-50" name="name" type="text" placeholder="Коротка назва вакансії" style="text-align: center;" id="name" required/>

                <label for="skills">Навички кандидата</label>
                <input class="form-control m-1" type="text" data-role="tagsinput" name="features" style="text-align: center" id="skills" required/>

                <label for="range">Досвід кандидата</label>
                <input type="range" name="exp" value="0" min="0" max="10" step="1" id="range"/>
                <p><output></output> років</p>

                <label for="salary">Орієнтовна З/П $</label>
                <input class="form-control w-25 m-3" type="number" name="salary" id="salary" step="100" required/>
            </div>
            <div class="col-sm-6 m-1 p-1 profile-block" style="max-height: 500px">
                <label for="range">Опис вакансії</label>
                <textarea class="form-control my-1 mr-3" name="story" placeholder="Опис вакансії - максимум 1024 символи" maxlength="1024" name="story" style="height: 250px; max-height: 350px" required></textarea>
            </div>
            <div align="center">
                <button class="btn btn-outline-dark m-1" type="submit">Створити</button>
            </div>
        </form>
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