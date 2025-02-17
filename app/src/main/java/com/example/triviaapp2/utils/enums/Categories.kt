package com.example.triviaapp2.utils.enums

import com.example.triviaapp2.R

enum class Categories(val value: String, val iconId: Int, val voice: Int) {
    GENERAL_KNOWLEDGE(
        value = "General Knowledge",
        iconId = R.drawable.general_knowledge,
        voice = R.raw.general_knowledge
    ),
    ENTERTAINMENT_BOOKS(
        value = "Entertainment: Books",
        iconId = R.drawable.entertainment_books,
        voice = R.raw.books
    ),
    ENTERTAINMENT_FILMS(
        value = "Entertainment: Film",
        iconId = R.drawable.entertainment_films,
        voice = R.raw.films
    ),
    ENTERTAINMENT_MUSIC(
        value = "Entertainment: Music",
        iconId = R.drawable.entertainment_music,
        voice = R.raw.music
    ),
    ENTERTAINMENT_MUSICALS_THEATRES(
        value = "Entertainment: Musicals &amp; Theatres",
        iconId = R.drawable.entertainment_musicals_theatres,
        voice = R.raw.musical_theatres

    ),
    ENTERTAINMENT_TV(
        value = "Entertainment: Television",
        iconId = R.drawable.entertainment_tv,
        voice = R.raw.television
    ),
    ENTERTAINMENT_VIDEO_GAMES(
        value = "Entertainment: Video Games",
        iconId = R.drawable.entertainment_video_games,
        voice = R.raw.video_games
    ),
    ENTERTAINMENT_BOARD_GAMES(
        value = "Entertainment: Board Games",
        iconId = R.drawable.entertainment_board_games,
        voice = R.raw.board_games
    ),
    SCIENCE_AND_NATURE(value = "Science &amp; Nature", iconId = R.drawable.science_and_nature, voice = R.raw.science_and_nature),
    SCIENCE_COMPUTERS(value = "Science: Computers", iconId = R.drawable.science_computers, voice = R.raw.computers),
    SCIENCE_MATHEMATICS(value = "Science: Mathematics", iconId = R.drawable.science_mathematics, voice = R.raw.mathematics),
    MYTHOLOGY(value = "Mythology", iconId = R.drawable.mythology, voice = R.raw.mythology),
    SPORTS(value = "Sports", iconId = R.drawable.sports, voice = R.raw.sports),
    GEOGRAPHY(value = "Geography", iconId = R.drawable.geography, voice = R.raw.geography),
    HISTORY(value = "History", iconId = R.drawable.history, R.raw.history),
    POLITICS(value = "Politics", iconId = R.drawable.politics, R.raw.politics),
    ART(value = "Art", iconId = R.drawable.art, voice = R.raw.art),
    CELEBRITIES(value = "Celebrities", iconId = R.drawable.celebrities, voice = R.raw.celebrities),
    ANIMALS(value = "Animals", iconId = R.drawable.animals, voice = R.raw.animals),
    VEHICLES(value = "Vehicles", iconId = R.drawable.vehicles, voice = R.raw.vehicles),
    ENTERTAINMENT_COMICS(value = "Entertainment: Comics", iconId = R.drawable.entertainment_comics, voice = R.raw.comics),
    SCIENCE_GADGETS(value = "Science: Gadgets", iconId = R.drawable.science_gadget, voice = R.raw.gadgets),
    ENTERTAINMENT_ANIME_MANGA(
        value = "Entertainment: Japanese Anime &amp; Manga",
        iconId = R.drawable.entertainment_anime_manga,
        voice = R.raw.anime_and_manga
    ),
    ENTERTAINMENT_CARTOON_ANIMATIONS(
        value = "Entertainment: Cartoon &amp; Animations",
        iconId = R.drawable.entertainment_cartoon_animations,
        voice = R.raw.cartoon_and_animations
    )
}