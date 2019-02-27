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
    public static String startedWithSS;
    public static String robotMovesSS;
    public static int penalties;
    public static int groundC, groundH;
    public static Stack<Integer> stackMovesSS = new Stack<Integer>();
    public static Stack<Integer> stackCSMovesSS = new Stack<Integer>();
    public static String slots = "";

    //teleop
    public static int [] rocketScoredTO = new int[20];
    public static int [] CargoshipScoredTO = new int[2];
//    public static String robotMovesTO;
    //public static int penaltiesTO;
    public static Stack<Integer> stackMovesTO = new Stack<Integer>();
    public static Stack<Integer> stackCSMovesTO = new Stack<Integer>();
    public static Stack<Integer> stackUsedUpTO = new Stack<Integer>();


    //endgame/notes
    public static String driving = "";
    public static String accuracy = "";
    public static String defense = "";
    public static int unsupportedClimb = 5;
    public static int support = 0;
    public static String myNotes;
    public static boolean checked;
    public static Spinner dropdown;
    public static String unsure = "no";
}
