Propriétés à récupérer pour les entités :


Listing@delete

Listing@update

Listing@create

Listing@list
- uuid
- title
- price
- mileage
- producedAt
- images
    - path

Listing@show
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


User@delete

User@update (NE PAS INCLURE LES PROPRIETES ROLE ET ACTIVATION CODE !)
- birthAt
- phone
- siret
- photo

User@create (NE PAS INCLURE LES PROPRIETES ROLE ET ACTIVATION CODE !)
- email
- birthAt
- password
- confirmedPassword

User@show
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


Model@delete

Model@create

Model@update

Model@list
- id
- name
- brand
  - name

Model@show :
- id
- name
- brand
    - name


Brand@delete

Brand@create

Brand@update

Brand@list
- id
- name
- Ajouter une méthode "modelCount" elle renvoie le nombre de Model pour la Brand

Brand@show
- id
- name
- models
    - id
    - name


Address@delete

Address@create

Address@update

Address@show
- id
- streetNumber
- streetName
- zipCode
- city
- latitude
- longitude


Image@delete

Image@create


Fuel@delete

Fuel@create

Fuel@update

Fuel@list
- id
- name
- logo

Fuel@show
- id
- name
- logo


Favorite@delete

Favorite@create


## Faire le(s) "Controller Advisor"


- Un controller advice pour gérer la "AlreadyActiveException"
- Un controller advice pour gérer la "ExpiredCodeException"


Renvoyer une réponse personnalisée, contenant un code d'erreur "503" et le message remonté de l'Exception 