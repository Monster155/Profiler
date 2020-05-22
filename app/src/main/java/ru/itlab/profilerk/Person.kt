package ru.itlab.profilerk

class Person constructor(
    name: String,
    surname: String,
    email: String,
    password: String,
    drawable: Int
) {
    private var name = name
    private var surname = surname
    private var logpass: HashMap<String, String> = HashMap()
    private var drawable: Int = drawable

    init {
        logpass.put(email, password)
    }

    fun getName(): String {
        return name
    }

    fun getSurname(): String {
        return surname
    }

    fun getDrawable(): Int {
        return drawable
    }

    fun getLogpass(): HashMap<String, String> {
        return logpass
    }
}
