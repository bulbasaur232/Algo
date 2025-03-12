import java.util.*;

class Solution {
    String begin;
    String target;
    String[] words;
    int min;
    
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        min = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(begin)){
                visited[i] = true;
            } 
        }
        
        List<String> list = Arrays.asList(words);
        if(list.contains(target)){
        solve(1, begin);          
        }        
        
        if(min == Integer.MAX_VALUE){
            return 0;
        } else {
            return min;
        }
    }
    
    void solve(int depth, String begin){
        if(swappable(begin, target)){
            min = Math.min(min, depth);
            return;
        }
        
        for(int i = 0; i < words.length; i++){
            if(!visited[i] && swappable(begin, words[i])){
                visited[i] = true;
                solve(depth + 1, words[i]);
                visited[i] = false;
            }
        }
    }
    
    boolean swappable(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        int differ = 0;
        
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                differ++;
            }
            
            if(differ > 1){
                return false;
            }
        }
        
        return true;
        
    }
    
}