<#import "macro/commonMac.ftl" as c>
<@c.page>
<div class="form-group mt-3">
    <h1>Редактировать сообщение</h1>
    <#list massages as mas>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" name="text" placeholder="Введите сообщение" value="${mas.text}" class="form-control">
        </div>
        <div class="form-group">
            <input type="text" name="tag" placeholder="Введите ваш тэг" value="${mas.tag}" class="form-control">
        </div>
        </#list>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-dark m">Обновить</button>
    </form>
</div>
</@c.page>