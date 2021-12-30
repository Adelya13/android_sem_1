package kpfu.itis.valisheva.app1.db

import androidx.room.*


@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Task?

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    fun deleteTaskById(id: Int)

    @Query("SELECT * FROM task")
    fun findAllTasks(): List<Task>

    @Query("DELETE FROM task")
    fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task)
}
