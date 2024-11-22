package com.example.practice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class Game(
    val dateTime: String,
    val homeTeam: String,
    val awayTeam: String,
    val location: String,
    val status: String
)

class GameAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTime: TextView = itemView.findViewById(R.id.game_datetime)
        val homeTeam: TextView = itemView.findViewById(R.id.home_team)
        val awayTeam: TextView = itemView.findViewById(R.id.away_team)
        val location: TextView = itemView.findViewById(R.id.game_location)
        val status: TextView = itemView.findViewById(R.id.game_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gamelist, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.dateTime.text = game.dateTime
        holder.homeTeam.text = game.homeTeam
        holder.awayTeam.text = game.awayTeam
        holder.location.text = game.location
        holder.status.text = game.status
    }

    override fun getItemCount() = games.size
}