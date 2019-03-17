<#assign page_title = "CMS Blog Home">

<#include "parts/header.ftl">


<ul class="list-group">
    <#list articles as article>
        <li class="list-group-item d-flex align-items-center">
        <a class="mr-auto" href="/articles/${article.id}">${article.title}</a>
        <#if isAuth >
            <a href="/articles/${article.id}/delete" class="btn btn-danger">Supprimer</a></li>
        </#if>
    </#list>
</ul>

<#include "parts/footer.ftl">
