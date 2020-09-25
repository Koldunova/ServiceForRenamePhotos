package by.vitebsk.energo.koldunova.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import by.vitebsk.energo.koldunova.entity.MyNote;

public class MySkipReader {
    public static List<MyNote> readData(ConnectionDB connection) {
        List<MyNote> dataFromDB = new ArrayList<>();

        try {
            final String sql = "SELECT [Num_Certificat] ,([W_Surname] + ' ' + [W_Name] + ' '+[W_Patronymic]) as fullName FROM [office].[dbo].[SkipNew]";
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString(2) != null) {
                    MyNote myNote = new MyNote();
                    myNote.setNum(resultSet.getInt(1));
                    myNote.setName(resultSet.getString(2));
                    
                    dataFromDB.add(myNote);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataFromDB;
    }
}
