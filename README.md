# Les amis de l'escalade
### Projet de site communautaire autour de l'escalade.
<p>Le but est la mise en relation et le partage d'informations. 
 Il donnera de la visibilité à l'association et encouragera des grimpeurs indépendants à y adhérer.

#### Les fonctionnalités :
<ul>
    <li>F1 : Un utilisateur doit pouvoir consulter les informations des sites d’escalades (secteurs, voies, longueurs, etc.).</li>
    <li>F2 : Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs critères pour trouver un site de grimpe et consulter le résultat de cette recherche. 
    Les critères peuvent être le lieu, la cotation, le nombre de secteurs, etc. </li>
    <li>F3 : Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.</li>
    <li>F4 : Un utilisateur connecté doit pouvoir partager des informations sur un site d’escalade (secteurs, voies, longueurs, etc.).</li>
    <li> F5 : Un utilisateur connecté doit pouvoir laisser des commentaires sur les pages des sites d’escalade. </li>
    <li> F6 : Un membre de l'association doit pouvoir taguer un site d’escalade enregistré sur la plateforme comme « Officiel Les amis de l’escalade ».</li>
    <li> F7 : Un membre de l'association doit pouvoir modifier et supprimer un commentaire.</li>
    <li> F8 : Un utilisateur connecté doit pouvoir enregistrer dans son espace personnel les topos qu’ils possèdent et préciser si ces derniers sont disponibles pour être prêtés ou non. Un topo est défini par un nom, une description, un lieu et une date de parution.</li>
    <li> F9 : Un utilisateur connecté doit pouvoir consulter la liste des topos disponibles par d’autres utilisateurs et faire une demande de réservation. La réservation n’inclut pas les notions de date de début et date de fin.</li>
    <li> F10 : Un utilisateur connecté doit pouvoir accepter une demande de réservation. Si une réservation est acceptée, automatiquement le topo devient indisponible. L’utilisateur connecté pourra ensuite de nouveau changer le statut du topo en « disponible ». 
    La plateforme se contente de mettre en contact les deux parties pour le prêt d’un topo (par échange de coordonnées).</li>
    <li> F11: L’utilisateur peut supprimer un topo papier. (en cas de perte du doccument par ex)</li>
    <li> F12: Un membre de l’association loggé peut supprimer un site.</li>
    <li> F13: Un membre de l’association peut détagué un site.</li>
    <li> F14: Un membre de l'association ou celui qui à crée l'élement (site/secteur/voie/TopoPapier) peut modifier les informations de cet élément.</li>
    <li> F15: Un membre de l'association peut voir la liste des utilisateurs enregistrés et supprimer un utilisateur au besoin (cela ne supprime pas le contenu ajouté par l'utilisateur, mais supprime ses topos).</li>
</ul>
<p>Toutes les fonctions dans cette application respectent la logique de persistance des données (CRUD)

####Les contraintes fonctionnelles
<ul>
    <li>Vocabulaire de l'escalade utilisé.</li>
    <li>Le site est responsive.</li>
    <li>le site est sécurisé. (aucun mot de passe est stocké en clair dans la BDD)</li>
</ul>

### Deploiement

<p>Installer le JDK d'Oracle version minimum 8. 
<ol>
 <li>Cloner le répertoire</li>
   <li>Installer Maven version minimum 2.</li>
   <li>Installer Tomcat 9.0.14.</li>
   <li>Créer une base de données Postgresql.</li>
   <li>Dans le fichier \src\main\resources\database.properties, modifier les champs suivants:
   <ul>
      <li>le nom de votre de données: jdbc.url = jdbc:postgresql://localhost:5432/"nom-de-la-base-de-données" </li>
      <li>votre username: jdbc.username = "username" </li>
      <li>votre mot de passe: jdbc.password = "password" </li>
   </ul>
   </li>
 </ol>
<ol>
    <li>Lancement directement depuis l'IDE (développé avec IntelliJ)
        <ul>
            <li>Lancer le run du serveur</li>
            <li>Dans la console de l'IDE, lancer la commande mvn flyway:baseline</li>
            <li>Dans la console de l'IDE, lancer la commande mvn flyway:migrate</li>
        </ul>
    </li>
    <li>Lancement avec le war
        <ul>
            <li>Récupérer le script sql dans db/migration et le lancer dans la base de données créée</li>
            <li>Dans le terminal lancez la commande mvn package</li>
            <li>Copier le fichier war généré dans le dossier target et le coller dans le dossier webapps de tomcat</li>
            <li>Lancez le serveur tomcat et rendez vous à l'adresse http://localhost:8080/amisEscalade-1.0/</li>
        </ul>
    </li>
</ol>
<p> Vous pouvez maintenant accéder au site, pour vous connecter utilisez: login "admin", mdp "admin"


### Futures évolutions

<ol>
<li>Pour l'inscription en tant que membre:
    <ul>
        <li>Ajouter un champ pour saisir son numéro de membre</li>
        <li>Le comparer avec une table de bdd contenant la liste des  membres de l'association</li>
        <li>Si le numéro n'est pas reconnu, champ erreur "ce numéro n'est pas un numéro de membre"</li>
    </ul>
</li>
<li>Pour la newsletter:
    <ul>
        <li>Prévoir batch pour un envoi de mail automatique</li>
        <li>Proposer l'inscription ou la désinscription depuis le profil utilisateur</li>
    </ul>
</li>
<li>Pour les spots:
    <ul>
        <li>Ajouter le champ pays</li>
    </ul>
</li>
<li>
    Pour l'enregistrement de données géographiques (ville, région etc.):
    <ul>
        <li>Implémenter l'api rest geo.gouv.fr pour l'intégrité des données</li>
    </ul>
</li>
</ol>


