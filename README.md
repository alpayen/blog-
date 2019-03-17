# blog-kotlin

## Rendu 17/03/2019

### Un petit blog fait en Kotlin à l'aide de Ktor

#### Installation : 

>Import de la base de données en local à l'adresse : 
> mysql://localhost:3306/blog_kotlin
> - Adresse : Localhost
> - Port 3306
> - BBD Name : blog_kotlin

> Build de l'application depuis main.kt


>Login sur l'application : 
> - username : admin
> - password : KTcCOOL
### Fonctionalités

- [FAIT] Affichage des commentaires d'un article (table commentaire : id, idArticle, text).
- [FAIT] Possibilite de poster un commentaire (sans etre connecte) depuis la page d'un article.
- [FAIT] Connection a une interface d'administration avec login / mot de passe.
- [FAIT] Gestion de la session.
- [FAIT] Possibilite de se deconnecter.
- [FAIT] Une fois connecte en tant qu'admin, possibilite d'ajouter ou supprimer un article.
- [FAIT] Si on supprime un article, ses commentaires doivent etre supprimes (soit manuellement, soit via les MySQL Foreign keys).
- [FAIT] Une fois connecte en tant qu'admin, possibilite de supprimer un commentaire.
- [TODO] Les controlleurs correspondant a ces nouvelles fonctionalites doivent etre correctement testes.

### Extra

- [FAIT] Jolie interface utilisateur avec CSS externe
- [TODO] Test du model via H2
- [FAIT] Utilisation de BCrypt