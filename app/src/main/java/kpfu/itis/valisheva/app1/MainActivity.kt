package kpfu.itis.valisheva.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wizard = Wizard("Alex", 25,true,"mental","Academy")
        //var wizard2 = Wizard("Jon", 24,true,"water","Academy")

        var warrior1 = Warrior("Rose", 20, false, "sword", 5 )
        var warrior2 = Warrior("Kate", 22, false, "sword", 6)

        wizard.castSpell()
        wizard.brewPotion()
        wizard.conductRitual()

        warrior1.challenge(warrior2)
        warrior1.conductSparring(warrior2)
        warrior1.fightTheMonster()






    }
}

