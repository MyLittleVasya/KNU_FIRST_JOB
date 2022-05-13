<#import "parts/starter.ftlh" as starter>

<@starter.page>
    <div class="profile-body col-sm" align="center">
        <form action="/updateProfile" method="post">
            <input type="hidden" value="${userProfile.id}" name="user_id"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input class="input_text m-1" name="pib" placeholder="ПІБ" type="text" value="${userProfile.profile.PIB?ifExists}" style="width: 50%; text-align: center"/>
            <div class="row m-1">
                <div class="col-sm profile-block m-1">
                    <div class="row profile-block">
                       <img class="img-fluid m-1" src="../static/img/standart_avatar.png"  style="height: 256px; width: 256px;">
                        <div class="col-sm profile-block">
                        <div class="row profile-block m-1 mt-2">
                            <div class="profile-block mx-1">
                                Університет:
                                <input class="input_text" name="university" type="text" value="${userProfile.profile.university?ifExists}"/>
                            </div>
                        </div>
                        <div class="row profile-block m-1">
                            <div class=" mx-1">
                                Спеціальність:
                                <input class="input_text" name="spec" type="text" value="${userProfile.profile.specialization?ifExists}"/>
                            </div>
                        </div>
                        <div class="row profile-block m-1" style="height: 50px;">
                            <div class="mx-1">
                                Досвід:
                                <input type="range" name="exp" value="${userProfile.profile.experience?ifExists}" min="0" max="10" step="1" id="range"/>
                                <p><output></output> років</p>
                            </div>
                        </div>
                        <div class="row profile-block m-1" style="height: 50px;">
                            <div class="mx-1">
                                Бажана форма роботи:
                                <select class="form-select m-1" name="formOfWork">
                                    <#if userProfile.profile.formOfWork??>
                                        <option selected value="${userProfile.profile.formOfWork}">${userProfile.profile.formOfWork}</option>
                                    </#if>
                                    <option value="Офіс">Офіс</option>
                                    <option value="Remote">Remote</option>
                                    <option value="Part-time">Part-time</option>
                                    <option value="Фріланс">Фріланс</option>
                                    <option value="With relocate">With relocate</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm profile-block text-center m-1">
                    <div>Навички</div>
                    <input class="m-1" type="text" value="${feature}" data-role="tagsinput" name="features"/>
            </div>

        </div>
    <div class="row m-1">
        <div class="col-sm profile-block m-1" style="height: 175px">
            Коротко про себе
            <textarea class="form-control m-1" name="story" placeholder="Коротко про себе" maxlength="512" name="story" style="height: 100px; max-height: 75%">${userProfile.profile.story?ifExists}</textarea>
        </div>
    </div>
        <div class="row m-1">
            <div class="col-sm profile-block m-1">
                <div class="row">
                    <div class="col-sm profile-block m-1">
                        Оцінки
                        <input class="input_text m-1" name="marks" type="text" value="${userProfile.profile.marks?ifExists}"/>
                    </div>
                    <div class="col-sm profile-block m-1">
                        Связь с тобой
                        <input class="input_text m-1" name="contact" type="text" value="${userProfile.profile.communication?ifExists}"/>
                    </div>
                </div>
            </div>
            <div class="col-sm profile-block m-1 m-1">
                Пет-проект(посилання на репозиторій або аккаунт GitHub)
                <p><input class="input_text m-1" name="pet" type="text" value="${userProfile.profile.petProject?ifExists}" style="width: 100%"/></p>
            </div>
            <div class="col-sm profile-block m-1">
                Резюме
                <p><input class="input_text m-1" name="cv" type="text" value="${userProfile.profile.cv?ifExists}"/></p>
            </div>
        </div>
            <div align="center">
                <button class="btn btn-outline-dark m-1" type="submit">Оновити</button>
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