Propriétés à récupérer pour les entités :


### SecurityController


@login

-> Param : UserLoginDTO :
- email
- password

@register

-> UserRegisterDTO :
- email
- birthAt
- password
- confirmedPassword


(Prévoir le JsonView de sortie)


### ListingController

@delete

@update
ListingUpdateDTO :
- description
- mileage
- price
- title

@create
ListingCreateDTO :
- description
- mileage
- price
- title
- producedAt
- address
- fuel
- model
- user (connecté ?)

@list (Attributs du JsonView)
- uuid
- title
- price
- mileage
- producedAt
- images
    - path

@show (Attributs du JsonView)
- uuid
- title
- price
- mileage
- description
- producedAt
- createdAt
- images
    - path
- owner
    - uuid
    - email
    - isActive
- fuel
    - type
- model
    - name
    - brand
        - name
- address
    - streetNumber
    - streetName
    - zipCode
    - city
    - latitude
    - longitude


### UserController

@delete

Ce n'est pas une vraie suppression, on renomme simplements les champs du user, on doit le conserver !

@update (NE PAS INCLURE LES PROPRIETES ROLE ET ACTIVATION CODE !)
- birthAt
- phone
- siret
- photo

@show
- uuid
- email
- phone
- siret
- photo
- birthAt
- createdAt
- isActive (faire une méthode isActive qui renvoie "activationCode == null")
- address
    - streetNumber
    - streetName
    - zipCode
    - city
    - latitude
    - longitude
- favorites
    - uuid
    - title
    - price
    - mileage
    - producedAt
    - images
        - path


### ModelController


@delete

Ce n'est pas une vraie suppression, on renomme simplements le label, on doit conserver l'objet !

@create
- name
- brand (id ?)

@update
- name

@list  (Attributs du JsonView)
- id
- name
- brand
  - name

@show  (Attributs du JsonView)
- id
- name
- brand
    - name


### BrandController


@delete

Ce n'est pas une vraie suppression, on renomme simplements le label, on doit conserver l'objet !


@create
- name

@update
- name

@list  (Attributs du JsonView)
- id
- name
- Ajouter une méthode "modelCount" elle renvoie le nombre de Model pour la Brand

@show  (Attributs du JsonView)
- id
- name
- models
    - id
    - name


### BrandController


@delete

Ce n'est pas une vraie suppression, on renomme simplements le label, on doit conserver l'objet !

@create
- streetNumber
- streetName
- zipCode
- city
- latitude
- longitude

@update
- streetNumber
- streetName
- zipCode
- city
- latitude
- longitude

@show
- id
- streetNumber
- streetName
- zipCode
- city
- latitude
- longitude


### ImageController


@delete

@create
- path
- listing (id ?)


### FuelController


@delete

Ce n'est pas une vraie suppression, on renomme simplements le label, on doit conserver l'objet !


@create
- logo
- type

@update
- logo
- type

@list
- id
- name
- logo

@show
- id
- name
- logo


### FavoriteController


@handFavorite

FavoriteDTO :
- listingId
- userId


## Faire le(s) "Controller Advisor" (BONUS !)


- Un controller advice pour gérer la "AlreadyActiveException"
- Un controller advice pour gérer la "ExpiredCodeException"


Renvoyer une réponse personnalisée, contenant un code d'erreur "503" et le message remonté de l'Exception 