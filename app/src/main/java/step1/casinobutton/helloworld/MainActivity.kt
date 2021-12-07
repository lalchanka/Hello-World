package step1.casinobutton.helloworld

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

enum class SoundFiles(val soundId: Int) {
    HUIPI(R.raw.huipi),
    BULLSHIT(R.raw.i_fk_your_bull_sht),
    SUKA_YOBANAYA(R.raw.suka_yobanaya_padla_blyadskaya),
    BREDISH_CHTOLI(R.raw.ty_cho_bredish_chtoli),
    BREDISH_SUKA(R.raw.ty_cho_bredish_suka),
    DURAK_BLYAT(R.raw.ty_cho_durak_blyat),
    KTO_SUKA(R.raw.ty_kto_takoi_suka),
    GDE_ZARYAZHAETE(R.raw.vy_cho_v_kioskah_ih_zaryazhaete),
    YOBANIYROT(R.raw.yobani_rot_etogo_kasino_blyat),
    PORYADOK_NETOT(R.raw.yobaniy_tvoi_rot_kakogo_huya_oni)
}

const val GREETING_CAPTION = "Welcome to the Casino World!"
const val UNDERBUTTON_CAPTION = "PRESS ME (:"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var soundIdList: MutableList<Int>
    private lateinit var textViewGreetings: TextView
    private lateinit var textViewUnderButton: TextView
    private var sounds: SoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewGreetings = findViewById(R.id.textView)
        textViewGreetings.text = GREETING_CAPTION

        textViewUnderButton = findViewById(R.id.textView2)
        textViewUnderButton.text = UNDERBUTTON_CAPTION

        sounds.setOnLoadCompleteListener { soundPool, sampleId, status ->
            Log.d("MY", "Complete load sampleId = $sampleId Status = $status")
        }
        soundIdList = loadSounds()
    }

    private fun loadSounds(): MutableList<Int> {
        val soundIdList = mutableListOf<Int>()

        for (name in SoundFiles.values()) {
            soundIdList.add(
                sounds.load(baseContext, name.soundId, 0)
            )
        }

        return soundIdList
    }

    fun playSound(view: android.view.View) {
        sounds.play(soundIdList.random(), 1F, 1F, 0, 0, 1F)
    }
}