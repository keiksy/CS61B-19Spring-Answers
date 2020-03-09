public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ans = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }

    public boolean isPalindrome(String word) {
        return helper(0, word.length()-1, word);
    }

    private boolean helper(int h, int t, String word) {
        if(h>=t) {
            return true;
        } else if (h == t-1) {
            return word.charAt(h)==word.charAt(t);
        } else {
            return word.charAt(h)==word.charAt(t) ? helper(h+1, t-1, word) : false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return helper2(0, word.length()-1, word, cc);
    }

    private boolean helper2(int h, int t, String word, CharacterComparator cc) {
        if (h>=t) {
            return true;
        } else if (h==t-1) {
            return cc.equalChars(word.charAt(h), word.charAt(t));
        } else {
            return cc.equalChars(word.charAt(h), word.charAt(t)) ? helper2(h+1, t-1, word, cc) : false;
        }
    }
}
