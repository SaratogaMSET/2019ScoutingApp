package com.example.ranas.a2019scoutingapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Matches {

    private static int matchList[][] = new int[100][6];
    public static int ctr;

    public Matches(String matches) throws IOException {
        Scanner cin = new Scanner(new StringReader(matches));

        ctr = 0;
        while(cin.hasNextInt()){
            for(int x = 0; x < 6; x++){
                matchList[ctr][x] = cin.nextInt();
            }
            ctr++;
        }
    }

    public int[][] getMatchList(){
        return matchList;
    }
}
