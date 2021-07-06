<#import "macro/commonMac.ftl" as h>
<#import "macro/loginMac.ftl" as log>

<@h.page>
<h1>Регистрация нового пользователя</h1>
${massage?ifExists}
<@log.login "/registration" true/>
</@h.page>