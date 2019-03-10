package com.example.ranas.a2019scoutingapp;

import android.widget.Spinner;

import java.util.Stack;

public class Vars {
    //main
    public static String myTeamNumber = "";
    public static String myMatchNumber = "0";
    public static String myScouterName = "";
    public static String alliance = "";
    public static String tabNum = "";
    public static Stack<String> universal = new Stack<String>();

    //sandstorm
    public static String ssPos;
    public static int [] rocketScoredSS = new int[20];
    public static int [] CargoshipScoredSS = new int[2];
    public static String startedWithSS = "NaN";
    public static String robotMovesSS = "";
    public static int penalties = 0;
    public static int groundC = 0, groundH = 0;
    public static Stack<Integer> stackMovesSS = new Stack<Integer>();
    public static Stack<Integer> stackCSMovesSS = new Stack<Integer>();
    public static String slots = "";
    public static int exitHab = 0;

    //teleop
    public static int [] rocketScoredTO = new int[20];
    public static int [] CargoshipScoredTO = new int[2];
//    public static String robotMovesTO;
    //public static int penaltiesTO;
    public static Stack<Integer> stackMovesTO = new Stack<Integer>();
    public static Stack<Integer> stackCSMovesTO = new Stack<Integer>();
    public static Stack<Integer> stackUsedUpTO = new Stack<Integer>();


    //endgame/notes
    public static String driving = new String("NA");
    public static String accuracy = new String("NA");
    public static String defense = new String("NA");
    public static int unsupportedClimb = 0;
    //public static int support = 0;
    public static String myNotes = "";
    public static Spinner dropdown;
    public static String unsure = "";
}
