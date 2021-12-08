package kpfu.itis.valisheva.app1.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kpfu.itis.valisheva.app1.databinding.ItemTrackBinding
import kpfu.itis.valisheva.app1.models.Track

class TrackHolder (
    private val binding: ItemTrackBinding,
    private val action: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Track) {
        with(binding) {
            tvTrack.text = item.title
            tvAuthor.text = item.author
            ivCover.setImageResource(item.cover)
        }
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = TrackHolder(
            ItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}

