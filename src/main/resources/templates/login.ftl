<#assign page_title = "Login">

<#include "parts/header.ftl">

<#if message != "" >
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong>Attention</strong> ${message}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</#if>

<div class="card">
    <div class="card-body">
        <h5 class="card-title">Connexion</h5>
        <form action="/login" method="POST" enctype="application/x-www-form-urlencoded">
            <div class="form-group">
                <label for="username">Nom d'utilisateur</label>
                <input type="username" class="form-control" id="username" name="username" placeholder="Nom d'utilisateur">
            </div>
            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Mot de passe">
            </div>
            <button type="submit" class="btn btn-primary">Connexion</button>
        </form>
    </div>
</div>
<#include "parts/footer.ftl">
