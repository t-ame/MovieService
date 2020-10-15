# Starwars Movie Service

A web service to list the names of Star Wars movies along with their opening crawls and character. Also, anonymous comments can also be added and retrieved.

<br>

**Base URL**: <br><br> <http://ec2-52-14-66-3.us-east-2.compute.amazonaws.com:8080/Wars>

<br>

#### Content List

- [Response Format Info](#response-format-info)
- [Movies](#movies)
- [Characters](#characters)
- [Comments](#comments)

<br>

#### Response Format Info

**ERROR**

Status Code: **500** 

>Response Body: 

``` json
{
    "error_message": "An error occured. Please try again later."
}
```

**SUCCESS**

Status Code: **200** 

>Response Body: As specified for each endpoint below

<br>

#### Movies

**Fetch list of movies with data**

>**GET** */movies* 

>Response Body: 

``` json
{
    "result": [
        {
            "title": "A New Hope",
            "episode_id": 4,
            "opening_crawl": "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
            "release_date": "1977-05-25",
            "commentCount": 3,
            "comments": "/movies/4/comments",
            "charactersURL": "/movies/4/characters"
        }
    ],
    "count": 1,
    "self": "{baseURL}/movies"
}
```

<br>

#### Characters

**Fetch list of characters in a movie**

>**GET** */movies/{episodeID}/characters* 

>Query Parameters:

>- **filtergender**: Filter by gender. e.g 

>>`male, female, hermaphrodite, etc.`

>- **sortfield**: Sort by field. Options include; 

>>`name, gender, height`

>- **sortdirection**: Sorting direction. Options include; 

>>`asc, desc`

>Response Body: 

``` json
{
    "result": [
        {
            "name": "Wilhuff Tarkin",
            "height": 180,
            "mass": "unknown",
            "hair_color": "auburn, grey",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "64BBY",
            "gender": "male",
            "homeworld": "http://swapi.dev/api/planets/21/"
        }
    ],
    "totalCount": 18,
    "totalMatch": 1,
    "totalheighCm": 3066,
    "totalheighFt": "100",
    "totalheighIn": "7.09",
    "self": "{baseURL}/movies/4/characters"
}
```

<br>

#### Comments

**Add New Comment to a movie**

>**POST** */movies/{episodeID}/comments* 

>Request Body: 

``` json
{
	"comment": "A comment that is not more than 500 words"
}
```

>Response Body: 

``` json
{
    "comment": {
        "commentId": 3,
        "movieId": 4,
        "comment": "A comment that is not more than 500 words",
        "ipAddress": "0:0:0:0:0:0:0:1"
    },
    "self": "{baseURL}/movies/4/comments/3"
}
```

**Fetch list of all anonymous comments made on a movie**

>**GET** */movies/{episodeID}/comments* 

>Response Body: 

``` json
{
    "result": [
        {
            "commentId": 2,
            "movieId": 4,
            "comment": "Another test comment",
            "date_time": "2020-10-15 02:13:25",
            "ipAddress": "0:0:0:0:0:0:0:1",
            "self": "{baseURL}/movies/4/comments/2"
        }
    ],
    "count": 1,
    "self": "{baseURL}/movies/4/comments"
}
```

**Fetch a comment by comment ID**

>**GET** */movies/{episodeID}/comments/{commentID}* 

>Response Body: 

``` json
{
    "comment": {
        "commentId": 1,
        "movieId": 4,
        "comment": "A test comment",
        "date_time": "2020-10-15 02:00:49",
        "ipAddress": "0:0:0:0:0:0:0:1"
    },
    "self": "{baseURL}/movies/4/comments/1"
}
```

