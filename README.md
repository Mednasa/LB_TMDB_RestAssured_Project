# TMDB API

The project was created using the API of the TMDB page. You can find the description of the functions and their usage at https://developers.themoviedb.org/3/getting-started/introduction.

In order to use the project, you must be a member of the TMDB page and then the authorization procedures must be done. After getting the API key from the settings page, session_id should be obtained from https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id page.

API key, session_id and username must be written into the variables in the file in \src\main\java\com\tmdb\spec\RequestSpec.java.

## Test Cases & Contributors

| Test Cases                                     | Contributor                  |
|------------------------------------------------|------------------------------|
| [5-7-9-10]| [Ahmet Kaya](https://github.com/0AhmetKaya0)|
| [17-14-8-11-1-2-3-4] | [Onur Girgin](https://github.com/Mednasa) |
| [6-8-12-13] | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) |
| [19-18-16-15] | [Erdem Gürel](https://github.com/artam109) |



## Team Members

| Name                    | Role            | GitHub                                           | 
|-------------------------|-----------------|--------------------------------------------------|
| Nigar Ayla Özcanan      | QA Automation Engineer     | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) | 
| Ahmet Kaya              | QA Automation Engineer     | [Ahmet Kaya](https://github.com/0AhmetKaya0) | 
| Erdem Gürel             | QA Automation Engineer     | [Erdem Gürel](https://github.com/artam109)       |    
 | Onur Girgin             | Team Lead & QA Automation Engineer   | [Onur Girgin](https://github.com/Mednasa) |

### Function tests included in the project:

#### Account
- Get Details
- Add Movie To Favorite
- Add Movie To Watchlist
- Get Favorite Movies
- Get Rated Movies
- Get Watchlist Movies

#### Genres
- Get Movie List, Get TV List

#### Movie Lists
- Get Now Playing Movies
- Get Popular Movies
- Get Top Rated Movies
- Get Upcoming Movies

#### Movies
- Get etails
- Add Rating
- Add Movie
- Delete Rating

Note: It may give an error after creating lists 4 or 5 times with the CreateList function. Even if the lists are deleted, an error may occur while generating the list with the same explanation. If the description of the requestBody variable in the ListService class is changed and retried, the function will work without error.

## LICENSE
MIT LICENSE
