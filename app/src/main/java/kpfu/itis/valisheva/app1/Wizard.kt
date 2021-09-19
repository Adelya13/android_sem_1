package kpfu.itis.valisheva.app1



class Wizard(name: String, age: Int, gender: Boolean, val talent: String) : Human(name, age, gender), WizardI {

    private var education : String = "none";

    constructor(name: String,age: Int, gender: Boolean, talent: String, education : String) :
                                                                this(name, age, gender, talent) {
        this.education = education;

    }

    override fun castSpell() {
        println("A shadow under my right hand,\n" +
                "And on the left hand thimble is clear, like swelter,\n" +
                "And a ball of hot tar,\n" +
                "Do you have a more stronger needle?\n" +
                " \n" +
                "No, no, no, they cannot be embroidered, they cannot be sewn,\n" +
                "Ethereal creatures have no soul,\n" +
                "Hiding in the flames at the bottom,\n" +
                "Since at the bottom it is always colder.\n")
        println("The spell is done\n" + "--------------------------------------------------")
    }

    override fun brewPotion() {
        println("Night at my back, thief at the gates,\n" +
                "Distaff's hum won't let you sleep -\n" +
                "I am here yet again.\n" +
                "Some spin flax, some spin wool,\n" +
                "Some spin passion and some spin revenge,\n" +
                "And I shall spin your death.\n" +
                " \n" +
                "The wheel drives blood through the veins,\n" +
                "The wheel pours poison into the lips,\n" +
                "Wheel, keep spinning - it is I...\n" +
                "Hey, spinner, work faster,\n" +
                "Light fire, await the guests,\n" +
                "Pour wine and make the bed!..\n")
        println("The potion is done\n" + "--------------------------------------------------")
    }

    override fun conductRitual() {
        println("We don't need no hope, no faith\n" +
                "Anxiety is a sister to us\n" +
                "I memorized all the names of the heroes\n" +
                "Their pride, honor and passion\n" +
                "But in the end they'll be left with fear\n" +
                "And the breath of the plague\n" +
                "Will enter the Shadow lands\n" +
                "In the lumen of the clouds\n" +
                "The heaven bastion will tremble\n")

        println("The ritual is done\n" + "--------------------------------------------------")

    }

}

