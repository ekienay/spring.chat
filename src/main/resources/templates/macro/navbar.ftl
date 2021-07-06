<#include "security.ftl">
<#import "loginMac.ftl" as log>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">unnum.io</a>
    <button class="navbar-toggler btn btn-dark" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Massages</a>
            </li>
        </ul>
        <div class="navbar-text mr-4">
            ${name}
        </div>
        <@log.logout />
    </div>
</nav>