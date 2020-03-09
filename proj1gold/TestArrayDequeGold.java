import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 300; i++) {
            double ran = StdRandom.uniform();
            if (ran<0.25) {
                sb.append("addFirst("+i+")\n");
                stu.addFirst(i);
                sol.addFirst(i);
            } else if (ran<0.5) {
                sb.append("addLast("+i+")\n");
                stu.addLast(i);
                sol.addLast(i);
            } else if (ran<0.75) {
                sb.append("removeFirst("+i+")\n");
                Integer a = stu.removeFirst();
                Integer b = sol.removeFirst();
                assertEquals(sb.toString(), a, b);
            } else {
                sb.append("removeLast("+i+")\n");
                Integer a = stu.removeLast();
                Integer b = sol.removeLast();
                assertEquals(sb.toString(), a, b);
            }
        }
    }
}
