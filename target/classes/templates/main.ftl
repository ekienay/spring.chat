<#import "macro/commonMac.ftl" as c>
<#import "macro/security.ftl" as sec>
<@c.page>
<!--Search form-->
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск">
            <button type="submit" class="btn btn-dark ml-2">Поиск</button>
        </form>
    </div>
</div>
<!--Create a new Massage form-->
<a class="btn btn-dark" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Написать новое сообщение
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" placeholder="Введите сообщение" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" name="tag" placeholder="Введите ваш тэг" class="form-control">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                    <input type="file" name="file" id="customFile">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-dark m">Отправить</button>
        </form>
    </div>
</div>
<!--Massages lis-->
<div class="card-columns">
    <#list massages as massage>
    <div class="card text-white bg-dark my-5 border-dark mb-3 mb-3">
        <#if massage.fileName??>
        <img src="/img/${massage.fileName}" class="card-img-top">
    </#if>
    <div class="m-2">
        <span>${massage.text}</span>
        <b>${massage.tag}</b>
    </div>
    <div class="card-footer text-muted">
        ${massage.userName}
    </div>
    <#if massage.userName = sec.name || sec.isAdmin>
    <div class="row m-0 mb-2">
        <form action="/main/${massage.id}/edit" method="get">
            <button type="submit" class="btn btn-success m ml-2">Редактировать</button>
        </form>
        <form action="/main/${massage.id}/delete" method="get">
            <button type="submit" class="btn btn-success m ml-2">Удалить</button>
        </form>
    </div>
</#if>

</div>
<#else>
Massages not found
</#list>
</div>
</@c.page>