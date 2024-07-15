package com.example.personalgamerecommender

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerGenres: Spinner
    private lateinit var buttonRecommend: Button
    private lateinit var textTitle: TextView
    private lateinit var imageIcon: ImageView
    private lateinit var textDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerGenres = findViewById(R.id.spinner_genres)
        buttonRecommend = findViewById(R.id.button_recommend)
        textTitle = findViewById(R.id.text_title)
        imageIcon = findViewById(R.id.image_icon)
        textDescription = findViewById(R.id.text_description)

        // Set up the Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.genres_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGenres.adapter = adapter
        }

        // Set up Button listener
        buttonRecommend.setOnClickListener {
            val selectedGenre = spinnerGenres.selectedItem.toString()
            if (selectedGenre == "Choose") {
                textTitle.text = ""
                textDescription.text = "Please select a genre to get a recommendation."
                imageIcon.setImageDrawable(null)
            } else {
                val recommendation = getRecommendation(selectedGenre)
                textTitle.text = recommendation.first
                textDescription.text = recommendation.second
                imageIcon.setImageResource(recommendation.third)
            }
        }

        // Set up Spinner listener
        spinnerGenres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                // Optional: Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Do something when nothing is selected
            }
        }
    }

    private fun getRecommendation(genre: String): Triple<String, String, Int> {
        return when (genre) {
            "Action RPG" -> Triple(
                "Genshin Impact",
                "You and your sibling arrived here from another world. Separated by an unknown god, stripped of your powers, and cast into a deep slumber, you now awake to a world very different from when you first arrived.\n\nThus begins your journey across Teyvat to seek answers from The Seven — the gods of each element. Along the way, prepare to explore every inch of this wondrous world, join forces with a diverse range of characters, and unravel the countless mysteries that Teyvat holds...",
                R.drawable.genshin_impact
            )
            "Puzzle" -> Triple(
                "Monument Valley",
                "In Monument Valley you will manipulate impossible architecture and guide a silent princess through a stunningly beautiful world.\n\nMonument Valley is a surreal exploration through fantastical architecture and impossible geometry. Guide the silent princess Ida through mysterious monuments, uncovering hidden paths, unfolding optical illusions and outsmarting the enigmatic Crow People.",
                R.drawable.monument_valley
            )
            "Mystery" -> Triple(
                "An Elmwood Trail",
                "Solve the biggest mystery in the town of Riverstone surrounded by the Elmwood Forest. Find the missing girl and prove yourself to everyone.\n\nIt’s been 3 weeks since a young teen has gone missing and despite the town’s police's best efforts, they hit a dead end and declared the case of 18-year-old Zoey Leonard a runaway.",
                R.drawable.elmwood_trail
            )
            "Battle Royale" -> Triple(
                "PUBG",
                "PUBG: BATTLEGROUNDS is a battle royale that pits 100 players against each other. Players will land, loot, and survive in a shrinking battleground as they outplay their opponents to become the lone survivor.",
                R.drawable.pubg
            )
            "Strategy" -> Triple(
                "Clash of Clans",
                "Join millions of players worldwide as you build your village, raise a clan, and compete in epic Clan Wars!\n\nMustachioed Barbarians, fire wielding Wizards, and other unique troops are waiting for you! Enter the world of Clash!",
                R.drawable.clash_of_clans
            )
            "Time Killer" -> Triple(
                "Slidey",
                "Slidey is a brand new puzzle game that trains your brain!\n\nNow players can choose from 2 Skins: The CLASSIC one and also a new CATS theme!\n\nWe give you SLIDEY, the ultimate Puzzle game with unique gameplay and endless fun!\n\nThe Slidey puzzle is simple: slide blocks left or right to create and clear full lines. Slidey is free to play and easy to learn.\n\nJoin the wildest adventure of your life and have a blast! Slide and slider and SLIDEY!",
                R.drawable.slidey
            )
            else -> Triple(
                "Select a genre",
                "Select a genre to get a recommendation.",
                0 // No image
            )
        }
    }
}
