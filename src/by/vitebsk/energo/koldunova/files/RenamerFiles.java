package by.vitebsk.energo.koldunova.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import by.vitebsk.energo.koldunova.entity.MyNote;


public class RenamerFiles {

    public static void renameFiles(String path, List<MyNote> dataFromDB) {
        try {
            List<Path> paths=getPathAllFiles(path);
            String num="";
            for (Path pathFile : paths) {
                for (MyNote myNote : dataFromDB) {
                    String notenum = myNote.getNum();
                    if(notenum.length() != 5) {
                        int raz = 5-myNote.getNum().length();
                        String[] forCompareNameAndNum = new String[5];
                        for (int i=0;i<5;i++) {
                            forCompareNameAndNum[i]="0";
                        }
                        int counter=0;
                        for(int i=raz; i<5; i++) {
                            forCompareNameAndNum[i]=Character.toString(myNote.getNum().charAt(counter));
                            counter++;
                        }
                        for (String a : forCompareNameAndNum) {
                            num+=a;
                        }
                        num+=".jpg";
                    }
                    if (pathFile.getFileName().toString().equals(num)) {
                        File file = new File(pathFile.toString());
                        File newFile = new File(pathFile.getParent().toString()+"\\"+myNote.getName() + ".jpg");
                        if(file.renameTo(newFile)){
                            System.out.println("Файл переименован успешно " + num);;
                        }else{
                            System.out.println("Файл не был переименован " + num);
                        }
                    }
                    num="";
                }
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private static List<Path> getPathAllFiles(String path) throws IOException{
        List<Path> paths = new LinkedList<Path>();
        try (Stream<Path> filePathStream=Files.walk(Paths.get(path))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    paths.add(filePath);
                }
            });
        }
        return paths;
    }
}
