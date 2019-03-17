<#assign page_title = "Ajouter un nouvelle article">

<#include "parts/header.ftl">

<div class="card">
    <div class="card-body">
        <h5 class="card-title">Ajouter un nouvelle article</h5>
        <form action="/new_article" method="POST" enctype="application/x-www-form-urlencoded">

            <div class="form-group">
                <label for="title">Email address</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Tire de l'article">
            </div>
            <div class="form-group">
                <label for="text">Example textarea</label>
                <textarea name="text" id="text" class="form-control" rows="3" placeholder="Contenu de l'article"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Ajouter l'article</button>
        </form>
    </div>
</div>

<#include "parts/footer.ftl">
