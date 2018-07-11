package com.github.rasouli.jmonkey.repel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;


public class Repel {

    public static Stream<String> start(InputStream in){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        return br.lines();
    }
}
