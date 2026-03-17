package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {


    public static void main(String[] args) {

        try{
            RandomAccessFile raf = new RandomAccessFile("data/random.txt", "rw");
            raf.writeInt(100);
            raf.writeChar('A');
            raf.writeBoolean(true);
            raf.seek(0);
            System.out.println(raf.readInt());
            System.out.println(raf.readChar());
            System.out.println(raf.readBoolean());

            raf.seek(4);
            System.out.println(raf.readChar());


            System.out.println(raf.getFilePointer());

            raf.seek(4);
            raf.writeChar('B');

            raf.seek(4);
            System.out.println(raf.readChar());




            raf.seek(raf.length());
            System.out.println("Положение указателя после boolean: " + raf.getFilePointer());
            raf.writeDouble(3.01);
            raf.seek(7);
            System.out.println(raf.readDouble());

        } catch (IOException e){
            e.printStackTrace();
        }


    }


}
