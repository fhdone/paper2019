package leetcode;

import java.util.Arrays;

public class S_820_ShortEncodingOfWords {

    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b)->b.length()-a.length());
        StringBuilder sb = new StringBuilder();
        for(String s:words){
            if(sb.indexOf(s+"#")==-1){
                sb.append(s+"#");
            }
        }
        return sb.length();
    }
    
    public static void main(String[] args){
        S_820_ShortEncodingOfWords s = new S_820_ShortEncodingOfWords();
        System.out.println(s.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
    }
    
    
}
