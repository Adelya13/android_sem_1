package kpfu.itis.valisheva.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kpfu.itis.valisheva.app1.databinding.ActivityMainBinding


private const val HOME_TAG  = "tag_home"
private const val SEARCH_TAG  = "tag_settings"
private const val SETTINGS_TAG  = "tag_settings"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        with(binding){

            HomeButton.setOnClickListener{
                supportFragmentManager.beginTransaction().run{
                    setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                    R.anim.enter_from_left,R.anim.exit_to_right)
                    replace(fragments.id, HomeFragment(), HOME_TAG)
                    addToBackStack(HOME_TAG)
                    commit()

                }
            }

            SearchButton.setOnClickListener{

                supportFragmentManager.beginTransaction().run{
                    setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right)
                    replace(fragments.id, SearchFragment(), SEARCH_TAG)
                    addToBackStack(SEARCH_TAG)
                    commit()
                }

            }

            SettingsButton.setOnClickListener{

                supportFragmentManager.beginTransaction().run{
                    setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right)

                    replace(fragments.id, SettingsFragment(), SETTINGS_TAG)
                    addToBackStack(SETTINGS_TAG)

                    commit()
                }
            }



        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}


