<!--SingIn and SingUp from-->
<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group">
        <label class="col-sm-2 col-form-label"> User Name :</label>
        <div class="col-sm-5">
            <input type="text" name="username" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label> Password: </label>
        <div class="col-sm-5">
            <input type="password" name="password" class="form-control"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a href="/registration"><p>Регистрация нового пользователя</p></a></#if>
<button type="submit" class="btn btn-dark"><#if isRegisterForm>Sign up<#else>Sign In</#if></button>
</form>
</#macro>
<!--LogOut button-->
<#macro logout>
<form action="/logout" method="post">
    <button type="submit" class="btn btn-success">Sign Out</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</#macro>