package kpfu.itis.valisheva.app1

class Warrior(name: String, age: Int, gender: Boolean) : Human(name, age, gender), WarriorI {
    var weapon : String = "none"
    var power : Int = 1

    constructor(name: String,age: Int,gender: Boolean,weapon:String) : this(name, age, gender){
        this.weapon=weapon;
    }
    constructor(name: String,age: Int,gender: Boolean,weapon:String, power : Int) : this(name, age, gender){
        this.weapon = weapon;
        this.power = power
    }

    override fun challenge(warrior: Warrior) {
        println(name+ ": I challenge you to a duel!!!")
        println(warrior.name+ ": I accept your challenge")
        println("------------Fight---------")
        if (warrior.power> power) println(warrior.name+ " is win")
        else if(warrior.power> power) println("$name is win")
        else println("Dead heat!")
        println("---------------------------------------------------")
    }

    override fun fightTheMonster() {
        println("I'l destroy you monster!!!\n -------------------------------------------\n")
    }

    override fun conductSparring(warrior: Warrior) {
        println("Now your powers: \n"+
                "$name = $power \n"+
                warrior.name + " = "+ warrior.power.toString())
        println("Let's start training")
        if (warrior.power> power) {
            power+=2
            warrior.power++
        }
        else if (warrior.power< power){
            power++
            warrior.power+=2
        }
        else {
            power++
            warrior.power++
        }
        println("Congratulation! Your new powers: \n"+
                "$name = $power \n"+
                 warrior.name + " = "+ warrior.power.toString())
        println("---------------------------------------------------")


    }
}