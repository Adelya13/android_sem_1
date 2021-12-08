package kpfu.itis.valisheva.app1.repositories

import kpfu.itis.valisheva.app1.R
import kpfu.itis.valisheva.app1.models.Track

object TrackRepository {
    private var id =0

    val tracks = arrayListOf(
        Track(
            id++,
            "Numb",
            "Linking Park",
            R.drawable.numb,
            R.raw.linkin_park_numb
        ),
        Track(
            id++,
            "Believer",
            "Imagine Dragons",
            R.drawable.imagine_dragons_cover,
            R.raw.imagine_dragons_believer
        ),
        Track(
            id++,
            "I'm so sorry",
            "Imagine Dragons",
            R.drawable.imagine_dragons,
            R.raw.imagine_dragons_im_so_sorry
        ),
        Track(
            id++,
            "Natural",
            "Imagin Dragons",
            R.drawable.imagine_dragons,
            R.raw.imagine_dragons_natural
        ),
        Track(
            id++,
            "Королевна",
            "Мельница",
            R.drawable.melnica,
            R.raw.melnica_korolevna
        ),
        Track(
            id++,
            "Чужой",
            "Мельница",
            R.drawable.melnica,
            R.raw.melnica_chugoi
        ),
        Track(
            id++,
            "Двери Тамерлана",
            "Мельница",
            R.drawable.melnica,
            R.raw.melnica_tamerlan
        ),
        Track(
            id++,
            "Княже",
            "Мельница",
            R.drawable.melnica,
            R.raw.melnica_knyage
        ),

    )

}

