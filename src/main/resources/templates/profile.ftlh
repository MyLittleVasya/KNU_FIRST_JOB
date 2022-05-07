<#import "parts/starter.ftlh" as starter>

<@starter.page>
    <div class="profile-body" xmlns="http://www.w3.org/1999/html">
        <div class="row m-1">
            <div class="col-sm profile-block m-1">
                <div class="row profile-block">
                       <img src="../static/img/standart_avatar.png" style="margin: 5px;">
                    <div class="col-sm profile-block">
                        <div class="row profile-block m-1 mt-2">
                            <div class="profile-block mx-1">
                                Університет: КНУ ім. Шевченка
                            </div>
                        </div>
                        <div class="row profile-block m-1">
                            <div class=" mx-1">
                                Спеціальність: Інженерія Програмного Забезпечення
                            </div>
                        </div>
                        <div class="row profile-block m-1" style="height: 50px;">
                            <div class="mx-1">
                                Досвід: немає :(
                            </div>
                        </div>
                        <div class="row profile-block m-1" style="height: 50px;">
                            <div class="mx-1">
                                Бажана форма роботи: remote
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm profile-block text-center">
                <form action="/updateFeatures" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="chip">Features</div>
                    <input type="text" value="${feature}" data-role="tagsinput" name="features"/>
                    <input type="hidden" value="${userProfile.id}" name="user_id"/>
                    <button class="btn btn-outline-dark mt-1" type="submit">Оновити</button>
                </form>
            </div>

        </div>
    <div class="row m-1">
        <div class="col-sm profile-block m-1" style="height: 175px">
            Напиши о своих страданиях
        </div>
    </div>
        <div class="row m-1">
            <div class="col-sm profile-block m-1">
                <div class="row">
                    <div class="col-sm profile-block m-1" style="height: 150px">
                        Оценочки
                    </div>
                    <div class="col-sm profile-block m-1">
                        Связь с тобой
                    </div>
                </div>

            </div>
            <div class="col-sm profile-block m-1">
                Твои пэт-проекты
            </div>
            <div class="col-sm profile-block m-1">
                Ссылки на резюме
            </div>
        </div>
    </div>
</@starter.page>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="../static/script/bootstrap-tagsinput.js"/>
<script>

</script>