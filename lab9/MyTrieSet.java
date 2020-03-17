import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private static class Node {
        boolean isEnd;
        HashMap<Character, Node> sons = new HashMap<>();

        Node(boolean end) {
            this.isEnd = end;
        }
    }

    enum Query {
        CONTAINS, SEARCH,
    }

    Node root = new Node(false);

    @Override
    public void clear() {
        root.sons.clear();
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0) return false;
        return traversalHelper(key, 0, root, Query.CONTAINS) != null;
    }

    private Node traversalHelper(String key, int curIdx, Node root, Query mode) {
        if (!root.sons.containsKey(key.charAt(curIdx))) return null;
        if (curIdx == key.length()-1) {
            if (!root.sons.get(key.charAt(curIdx)).isEnd && mode==Query.CONTAINS) return null;
            return root.sons.get(key.charAt(curIdx));
        }
        return traversalHelper(key, curIdx+1, root.sons.get(key.charAt(curIdx)), mode);
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0) return;
        addHelper(key, 0, root);
    }

    private void addHelper(String key, int curIdx, Node root) {
        if (curIdx >= key.length()) return;
        if (!root.sons.containsKey(key.charAt(curIdx))) {
            boolean isEnd = curIdx==key.length()-1;
            root.sons.put(key.charAt(curIdx), new Node(isEnd));
        } else {
            if (curIdx == key.length()-1) root.sons.get(key.charAt(curIdx)).isEnd = true;
        }
        addHelper(key, curIdx+1, root.sons.get(key.charAt(curIdx)));
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> ans = new ArrayList<>();
        Node lastCharOfPrefix = traversalHelper(prefix, 0, root, Query.SEARCH);
        if (lastCharOfPrefix == null) return ans;
        if (lastCharOfPrefix.isEnd) ans.add(prefix);
        prefixHelper(ans, lastCharOfPrefix.sons, new StringBuilder(prefix));
        return ans;
    }

    private void prefixHelper(List<String> ans, HashMap<Character, Node> sons, StringBuilder cur) {
        for(Map.Entry<Character, Node> entry : sons.entrySet()) {
            cur.append(entry.getKey());
            if (entry.getValue().isEnd) {
                ans.add(cur.toString());
            }
            prefixHelper(ans, entry.getValue().sons, cur);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }
}
