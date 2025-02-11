package com.example.triviaapp2.utils.enums

import com.example.triviaapp2.R

enum class Categories(name: String, iconId: Int) {
    GENERAL_KNOWLEDGE(name = "General Knowledge", R.drawable.general_knowledge),
    ENTERTAINMENT_BOOKS(name = "Entertainment: Books",R.drawable.entertainment_books),
    ENTERTAINMENT_FILMS(name = "Entertainment: Film",R.drawable.entertainment_films),
    ENTERTAINMENT_MUSIC(name = "Entertainment: Music",R.drawable.entertainment_music),
    ENTERTAINMENT_MUSICALS_THEATRES(name = "Entertainment: Musicals &amp; Theatres", R.drawable.entertainment_musicals_theatres),
    ENTERTAINMENT_TV(name = "Entertainment: Television", R.drawable.entertainment_tv),
    ENTERTAINMENT_VIDEO_GAMES(name = "Entertainment: Video games", R.drawable.entertainment_video_games),
    ENTERTAINMENT_BOARD_GAMES(name = "Entertainment: Board games", R.drawable.entertainment_board_games),
    SCIENCE_AND_NATURE(name = "Science &amp; Nature", R.drawable.science_and_nature),
    SCIENCE_COMPUTERS(name = "Science: Computers", R.drawable.science_computers),
    SCIENCE_MATHEMATICS(name = "Science: Mathematics", R.drawable.science_mathematics),
    MYTHOLOGY(name = "Mythology", R.drawable.mythology),
    SPORTS(name = "Sports", R.drawable.sports),
    GEOGRAPHY(name = "Geography", R.drawable.geography),
    HISTORY(name = "History", R.drawable.history),
    POLITICS(name = "Politics", R.drawable.politics),
    ART(name = "Art", R.drawable.art),
    CELEBRITIES(name = "Celebrities", R.drawable.celebrities),
    ANIMALS(name = "Animals", R.drawable.animals),
    VEHICLES(name = "Vehicles", R.drawable.vehicles),
    ENTERTAINMENT_COMICS(name = "Entertainment: Comics", R.drawable.entertainment_comics),
    SCIENCE_GADGETS(name = "Science: Gadgets", R.drawable.science_gadget),
    ENTERTAINMENT_ANIME_MANGA(name = "Entertainment: Japanese Anime &amp; Manga", R.drawable.entertainment_anime_manga),
    ENTERTAINMENT_CARTOON_ANIMATIONS(name = "Entertainment: Cartoon &amp; Animations", R.drawable.entertainment_cartoon_animations)
}