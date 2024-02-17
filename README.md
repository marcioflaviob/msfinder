
# Recommend me a movie based on a song!

## A website that recommends a movie based on a chosen song. 

### Successfully integrated three APIs, including OpenAI's API to use artificial intelligence to find a movie that is similar to the chosen song.


## About it

This project was really helpful to understand APIs and learn how to overcome challenges related to API inconsistencies and data complexities.

This was my first full-stack project, seamlessly integrating front and back-end components. Overcame several challenges, particularly on the front-end, an area I consider my web development growth edge


## APIs used

 - [Genius API](https://docs.genius.com/) _- for the song search_
 - [OpenAI API](https://openai.com/blog/openai-api) _- for the movie recommendation_
 - [TMDB API](https://developer.themoviedb.org/reference/intro/getting-started) _- for the movie info_

## API Documentation

#### Returns the list of songs

It returns a JSON with 10 entities of the object `Song` corresponding to what was passed in the parameter `q` without their lyrics.

```http
  GET /search
```

| Parameter   | Type | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `q` | `string` | The name of the song to be returned |

#### Returns the GPT response

It receives a JSON payload of the object `Song` selected by the user and it returns a `Response` entity. 

The `Response` entity contains the `Song` selected by the user and its lyrics, the `GPTRequest` with the movie information, the `Movie` object with the movie details that came from the TMDB API, and a `String` with the language selected.

##### To get the response in English
```http
  POST /en
```
##### To get the response in Portuguese
```http
  POST /br
```

| Payload   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `song`      | `JSON` | **Required**. The song selected by the user. |

#### Returns a different response

It receives a JSON payload with `Response` entity and generates another one with a different `Movie` and a different `GPTRequest` based on the same song.

```http
  POST /repeat
```

| Payload   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `response`      | `JSON` | **Required**. The request previously sent. |


## Screenshots
#### Song selection page
![Song selection page](https://i.imgur.com/x4DDEun.png)

#### Results page
![Results page](https://i.imgur.com/vHX8Okc.png)


## Stacks

**Front-end:** React Vite

**Back-end:** Java Spring Boot

