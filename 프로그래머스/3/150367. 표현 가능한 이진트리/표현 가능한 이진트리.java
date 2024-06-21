import java.util.*;

/*
주어진 숫자를 "포화"이진트리 형태로 바꾸기, 숫자 크기는 최대 1000조
1. 주어진 숫자를 2진수로 변환
2. 포화이진트리 노드 갯수에 맞게 패딩 <- 앞자리를 0으로 채워야 같은 숫자임
3. 포화이진트리 규칙에 맞는지 체크 <- 리프노드를 제외하고 0이 있으면 안됨
*/

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            long num = numbers[i];
            if(isConvertible(num)){
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    public boolean isConvertible(long num){
        // 패딩된 이진수가 반환 됨
        String binary = toBinary(num); 
        
        // 부모 노드가 0인데 자식 노드가 1인 경우가 있는지 판단
        // 가운데 루트노드부터 시작해서 아래로 내려가기
        int center = binary.length() / 2;
        
        return isPerfectTree(center, center / 2 + 1, binary);
    }
    
    public boolean isPerfectTree(int idx, int differ, String binary){
        if((idx & 1) == 0)
            return true;
        
        if(binary.charAt(idx) == '0'){
            if(binary.charAt(idx - differ) == '1' || binary.charAt(idx + differ) == '1')
                return false;
        }
        
        return isPerfectTree(idx - differ, differ / 2,binary) && isPerfectTree(idx + differ, differ / 2, binary);
    } 
    
    public String toBinary(long num){
        StringBuilder br = new StringBuilder();
        while(num > 1){
            if((num & 1) == 1){
                br.append("1");
            }else {
                br.append("0");
            }
            num = num >> 1;
        }
        br.append(num);
        
        int paddingSize = padding(br.length());
        for(int i = 0; i < paddingSize; i++){
            br.append("0");
        }
        
        return br.reverse().toString();
    }
    
    public int padding(int len){
        int standard = 1;
        int add = 0;
        while(standard < len){
            standard += (2 << add);
            add++;
        }
        return standard - len;
    }
}