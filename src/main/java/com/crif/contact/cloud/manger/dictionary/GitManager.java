package com.crif.contact.cloud.manger.dictionary;


import java.util.HashMap;
import java.util.Map;

public class GitManager {
    public static final Map<Command, Boolean> COMMANDS;
    static {
        Map<Command, Boolean> paths = new HashMap<>();

        paths.put(Command.CLONE, gitClone());
        paths.put(Command.CHECKOUT, gitCheckout());
        COMMANDS = paths;
    }

    private static Boolean gitClone(){
        System.out.println("git clone");
        return true;
    }

    private static Boolean gitCheckout(){
        System.out.println("git checkout");
        return true;
    }
}
