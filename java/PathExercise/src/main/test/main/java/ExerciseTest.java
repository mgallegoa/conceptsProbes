package main.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {

    private List<Exercise.Category> categories;
    private String idCategoryA = UUID.randomUUID().toString();
    private String idCategoryB = UUID.randomUUID().toString();
    private String idCategoryC = UUID.randomUUID().toString();
    private String idCategoryD = UUID.randomUUID().toString();
    private String idCategoryE = UUID.randomUUID().toString();
    private String idCategoryF = UUID.randomUUID().toString();
    private Exercise.Category categoryA = new Exercise.Category(idCategoryA, null, "A");
    private Exercise.Category categoryB = new Exercise.Category(idCategoryB, idCategoryA, "B");
    private Exercise.Category categoryC = new Exercise.Category(idCategoryC, idCategoryB, "C");
    private Exercise.Category categoryD = new Exercise.Category(idCategoryD, idCategoryC, "D");
    private Exercise.Category categoryE = new Exercise.Category(idCategoryE, idCategoryD, "E");
    private Exercise.Category categoryF = new Exercise.Category(idCategoryF, idCategoryE, "F");

    @BeforeEach
    void beforeEach(){
        categories = new ArrayList<>();
    }

    @Test
    void initialExampleOrderedCategories() {
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryC);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category A is \"A\"" + System.lineSeparator() +
                                   "the path for category B is \"A > B\"" + System.lineSeparator() +
                                   "the path for category C is \"A > B > C\"" + System.lineSeparator());
    }

    @Test
    void initialExampleUnOrderedCategoriesFirstB() {
        categories.add(categoryB);
        categories.add(categoryA);
        categories.add(categoryC);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category B is \"A > B\"" + System.lineSeparator() +
                "the path for category A is \"A\"" + System.lineSeparator() +
                "the path for category C is \"A > B > C\"" + System.lineSeparator());
    }

    @Test
    void initialExampleUnOrderedCategoriesFirstC() {
        categories.add(categoryC);
        categories.add(categoryA);
        categories.add(categoryB);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category C is \"A > B > C\"" + System.lineSeparator() +
                "the path for category A is \"A\"" + System.lineSeparator() +
                "the path for category B is \"A > B\"" + System.lineSeparator());
    }

    @Test
    void largeCategories() {
        categories.add(categoryC);
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryD);
        categories.add(categoryE);
        categories.add(categoryF);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category C is \"A > B > C\"" + System.lineSeparator() +
                "the path for category A is \"A\"" + System.lineSeparator() +
                "the path for category B is \"A > B\"" + System.lineSeparator() +
                "the path for category D is \"A > B > C > D\"" + System.lineSeparator() +
                "the path for category E is \"A > B > C > D > E\"" + System.lineSeparator() +
                "the path for category F is \"A > B > C > D > E > F\"" + System.lineSeparator()
        );
    }

    @Test
    void largeCategoriesBrokenParent() {
        categoryE.setParentId(null);
        categories.add(categoryC);
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryD);
        categories.add(categoryE);
        categories.add(categoryF);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category C is \"A > B > C\"" + System.lineSeparator() +
                "the path for category A is \"A\"" + System.lineSeparator() +
                "the path for category B is \"A > B\"" + System.lineSeparator() +
                "the path for category D is \"A > B > C > D\"" + System.lineSeparator() +
                "the path for category E is \"E\"" + System.lineSeparator() +
                "the path for category F is \"E > F\"" + System.lineSeparator()
        );
    }

    @Test
    void largeCategoriesDuplicateParent() {
        categoryE.setParentId(idCategoryB);
        categories.add(categoryC);
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryD);
        categories.add(categoryE);
        categories.add(categoryF);
        String result = Exercise.printPath(categories);
        assertEquals(result, "the path for category C is \"A > B > C\"" + System.lineSeparator() +
                "the path for category A is \"A\"" + System.lineSeparator() +
                "the path for category B is \"A > B\"" + System.lineSeparator() +
                "the path for category D is \"A > B > C > D\"" + System.lineSeparator() +
                "the path for category E is \"A > B > E\"" + System.lineSeparator() +
                "the path for category F is \"A > B > E > F\"" + System.lineSeparator()
        );
    }
}