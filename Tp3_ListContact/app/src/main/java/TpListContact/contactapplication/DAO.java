package TpListContact.contactapplication;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DAO {

    @Insert(onConflict =REPLACE)
    void insert(Contact contact);

    @Delete
    void reset(List<Contact> contacts);


    @Query("UPDATE CONTACT SET name= :name, job=:job,phone=:phone,email=:email where ID=:ID")
    void update(int ID, String name,String job, String email, String phone);


    @Query("SELECT * FROM CONTACT")
    List<Contact> getAll();

    @Delete
    void delete(Contact contact);

    @Query("select * from CONTACT where name = :name")
    Contact findContactByName(String name);
}
