<#assign page_title = article.title>

<#include "parts/header.ftl">


<div class="card">
    <div class="card-body">
        <#if isAuth>
            <a href="/articles/${article.id}/delete" class="btn btn-danger float-right">Supprimer</a>
        </#if>
        <h5 class="card-title mt-5">${article.title}</h5>
        <p class="card-text text-justify">${article.text}.</p>

        <br>

        <h5 class="card-title">Ajouter commentaire</h5>
        <form action="/articles/${article.id}/new_comment" method="post">
            <div class="form-group">
                <label for="comment" class="">Commentaire</label>
                <textarea name="comment" id="comment" cols="3" class="form-control"
                          placeholder="Votre commentaire"></textarea>
            </div>

            <input type="hidden" name="article_id" value="${article.id}">
            <button type="submit" class="btn btn-primary">Ajouter</button>
        </form>

        <ul class="list-group mt-5">
            <#list article.comments as comment>
                <li class="list-group-item d-flex align-items-center"><p class="mr-auto">${comment.text}</p>
                    <#if isAuth><a href="/delete_comment/${comment.id}/${article.id}" class="btn btn-danger">Supprimer</a></#if>
                </li>
            </#list>
        </ul>
    </div>
</div>
<#include "parts/footer.ftl">
