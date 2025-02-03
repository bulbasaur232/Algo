import java.util.*;

/*
1. dfs 돌기
2. 주어진 항공권은 모두 사용해야 합니다.
*/

class Solution {
    String[][] tickets;
    LinkedList<String> visited; // 현재까지 방문한 순서
    HashMap<String, LinkedList<String>> route; // 갈 수 있는 루트 저장
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        init();
        visited.add("ICN");
        dfs("ICN");
        
        String[] ret = new String[visited.size()];
        
        for(int i = 0; i < ret.length; i++){
            ret[i] = visited.get(i);
        }
        
        return ret;
    }
    
    public boolean dfs(String start){
        LinkedList<String> nextList = route.get(start);
        
        if(nextList == null){
            return false;
        }
        
        for(int i = 0; i < nextList.size(); i++){
            String next = nextList.get(i);
            
            visited.add(next);
            nextList.remove(i);
            if(visited.size() == tickets.length + 1){
                return true;
            }
            
            if(dfs(next) == true)
               return true;
            
            nextList.add(i, next);
            visited.remove(visited.size() - 1);
        }
        
        return false;
    }
    
    public void init(){
        visited = new LinkedList<>();
        route = new HashMap<>();
        
        // 루트 세팅
        Arrays.stream(tickets).forEach(arr -> {
            route.computeIfAbsent(arr[0], key -> new LinkedList<>());
            route.get(arr[0]).add(arr[1]);
        });
        
        for(List<String> list : route.values()){
            list.sort(Comparator.naturalOrder());
        }
            
    }
}