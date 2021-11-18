package kpfu.itis.valisheva.app1.repositories

import kpfu.itis.valisheva.app1.models.User

object UserRepository {
    private var cnt = 1
    val users = arrayListOf(
        User(
            cnt++,
            "Анастасия",
            "богиня сырников",
            "https://mykaleidoscope.ru/uploads/posts/2021-03/1617051802_3-p-dekor-torta-3.jpg",
        ),
        User(
            cnt++,
            "Чембер",
            "лучший кот",
            "data:image/jpeg;base64,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",

        ),
        User(
            cnt++,
            "Аделя",
            "Чембер - гад",
            "https://i.natgeofe.com/n/9135ca87-0115-4a22-8caf-d1bdef97a814/75552_square.jpg",
        ),
        User(
            cnt++,
            "Камиль",
            "в душе кот",
            "https://fatcatart.com/wp-content/uploads/2011/02/Malevich_Red_Square-cat-sm.jpg",
        ),
        User(
            cnt++,
            "Ильнас",
            "сижу в тик-токе",
            "https://i.pinimg.com/474x/f4/72/06/f47206e14861019d9d1429f8b5a4f72a.jpg",
        )
    )

    fun addUser(id:Int, user: User){
        if (id <= 0 ||id > users.size){
            user.id = cnt++
            users.add(user)
        }
        else{
            user.id = cnt++
            users.add(id - 1, user)
        }

    }

    fun deleteUser(rabbit: User){
        users.remove(rabbit)
    }

    fun userList(): ArrayList<User> {
        return users
    }
    fun getUserById(id : Int) : User?{
        for(user in users){
            if(id == user.id){
                return user
            }
        }
        return null
    }
}

