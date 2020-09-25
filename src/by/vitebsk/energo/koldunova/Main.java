package by.vitebsk.energo.koldunova;

import java.util.List;

import by.vitebsk.energo.koldunova.database.ConnectionDB;
import by.vitebsk.energo.koldunova.database.MySkipReader;
import by.vitebsk.energo.koldunova.entity.MyNote;
import by.vitebsk.energo.koldunova.files.RenamerFiles;
import by.vitebsk.energo.koldunova.reader.MyXmlReader;

public class Main {

    public static void main(String[] args) {
        String connectionString = MyXmlReader.readXmlConfig("ConnectionSender");
        if (!connectionString.equals("")) {
            ConnectionDB connectionDB = new ConnectionDB(connectionString);

            List<MyNote> notes = MySkipReader.readData(connectionDB);

            connectionDB.closeConnection();

            String pathFolder = MyXmlReader.readXmlConfig("PathFolder");
            if (!pathFolder.equals("")) {
                RenamerFiles.renameFiles(pathFolder, notes);
            } else {
                System.out.println("Could not find path");
            }

        } else {
            System.out.println("Could not find connection string in config");
        }

    }

}
