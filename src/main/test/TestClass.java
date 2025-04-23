import org.example.dsa02ca1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {
    @Test
    public void initialize() {
        DisJointSet disjointSet = new DisJointSet(5*2);
        assertNotNull(disjointSet);
        assertEquals(10, disjointSet.size);
        assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9}, disjointSet.getParent());
    }

    @Test
    void findBeforeUnion() {
        DisJointSet disjointSet = new DisJointSet(5*2);

        assertEquals(0, disjointSet.find(0));
        assertEquals(1, disjointSet.find(1));
        assertEquals(2, disjointSet.find(2));
        assertEquals(3, disjointSet.find(3));
        assertEquals(4, disjointSet.find(4));
        assertEquals(5, disjointSet.find(5));
        assertEquals(6, disjointSet.find(6));
        assertEquals(7, disjointSet.find(7));
        assertEquals(8, disjointSet.find(8));
        assertEquals(9, disjointSet.find(9));
    }

    @Test
    void findAfterUnion() {
        DisJointSet disjointSet = new DisJointSet(5*2);

        disjointSet.union(0,1);
        disjointSet.union(2,3);
        disjointSet.union(4,5);

        assertEquals(0,disjointSet.find(0));
        assertEquals(2,disjointSet.find(3));
        assertEquals(4,disjointSet.find(5));

        disjointSet.union(5,6);
        disjointSet.union(6,7);
        disjointSet.union(3,8);

        assertEquals(4,disjointSet.find(6));
        assertEquals(4,disjointSet.find(7));
        assertEquals(2,disjointSet.find(8));
    }




}

