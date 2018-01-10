/*
Fing longest word in dictionary that is a subsequence of a given string

Given a string S and a set of words D, find the longest word in D that is a subsequence of S.

Word W is a subsequence of S if some number of characters, possibly zero, can be deleted from S to form W, without reordering the remaining characters.

Note: D can appear in any format (list, hash table, prefix tree, etc.

For example, given the input of S = "abppplee" and D = {"able", "ale", "apple", "bale", "kangaroo"} the correct output would be "apple"

The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.
*/
import java.util.*;

class longestSubsequenceString {
    
    public static void toString(Map<String, List<Integer>> map) {
      for(Map.Entry<String, List<Integer>> entry : map.entrySet()) {
        System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue().toArray()));
      }
    }
    
    public static int binarySearch(Integer[] array, int start, int end, int tmp) {
        while(start <= end) {
          int mid = start + (end - start)/2;
          if(array[mid] > tmp) {
            end = mid - 1;
          }
          else {
            start = mid + 1; 
          }
        }
        return end + 1 == array.length?-1:array[end+1];
    }
    public static void main(String[] args) {
        List<String> D = new ArrayList<String>();
        D.add("able");
        D.add("ale");
        D.add("apple");
        D.add("bale");
        D.add("kangroo");

        //A comparator to sort the strings by length
        Collections.sort(D, new Comparator<String>() {
            public int compare(String a, String b) {
                return Integer.compare(b.length(), a.length());
            }
        });

        String S = "abppplee";

        //Preprocess S to account for the position in S each
        //alphabet occurs
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for(int i = 0; i < S.length(); i++) {
            List index = map.get(String.valueOf(S.charAt(i)));
            if(index == null) {
              index = new ArrayList<Integer>();
              index.add(i);
              map.put(String.valueOf(S.charAt(i)),index);
            }
            else {
              index.add(i);    
            }
        }
        for(String s:D) {
            int temp = -1;
            int newTemp = -1;    
            int count = 0;
            for(int i = 0; i < s.length(); i++) {
                count++;
                if(map.containsKey(String.valueOf(s.charAt(i)))) {
                  List<Integer> indices = map.get(String.valueOf(s.charAt(i)));
                  Integer[] indicesArray = new Integer[indices.size()];
                  indicesArray = indices.toArray(indicesArray);
                  newTemp = binarySearch(indicesArray, 0, indices.size()-1, temp);
                  if(newTemp > temp) {
                      temp = newTemp; 
                  }
                }
                else {
                    break;
                }
            }
            if(count == s.length()) {
                System.out.println(s);
                break;
            }
        }
       
    }
}