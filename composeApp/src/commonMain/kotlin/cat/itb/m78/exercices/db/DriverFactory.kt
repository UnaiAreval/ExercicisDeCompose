package cat.itb.m78.exercices.db

import app.cash.sqldelight.db.SqlDriver

expect fun createMessage(): SqlDriver
fun createDatabase(): Database {
    val message = createMessage()
    return Database(message)
}