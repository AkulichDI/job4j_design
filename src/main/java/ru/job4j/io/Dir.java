package ru.job4j.io;

import java.io.File;

public class Dir {


    /*
    1. Доработайте программу ru.job4j.io.Search.
    Программа должна запускаться с параметрами.
        Первый параметр - начальная папка.
        Второй параметр - расширение файлов, которые нужно искать.
    Необходимо добавить валидацию данных параметров.
    Валидацию параметров надо вынести в отдельный метод.
     */

    //Проверка параметров конфигурации
    private static void validateArgsConfiguration(String[] args){

        if ( args.length != 2){
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if ( args.length < 1){
            throw new IllegalArgumentException("Укажите искомый формат");
        }

    }

    private static void showFiles(String[] args){
        File file = new File(args[0]);
        if (!file.exists()){
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));

        }if (!file.isDirectory()){
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        // System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));

        for ( File el : file.listFiles()){
            System.out.println(String.format("Наименование: %-35s Размер: %1d", el.getName(), el.length()));
        }



    }




    public static void main(String[] args) {


        validateArgsConfiguration(args);
        showFiles(args);




    }



}
