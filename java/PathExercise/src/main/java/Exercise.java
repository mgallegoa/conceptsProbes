package main.java;

// Please complete the following exercise using Java Programming Language.
// Your solution should compile and execute successfully.

import java.util.*;

public class Exercise {
    public static class Category {
        // Define the following fields with getters and setters:
        //    id: a unique numeric identifier of the category
        //    parentId: id of the parent category or null if it doesn't have the parent
        //    name: a string representation of category name

        private String id;
        private String parentId;
        private String name;


        // Builder pattern (for easy unit testing)
        public Category(String id, String parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //I changed the firm of the method for unit testing
    public static String printPath(List<Category> categories) {
        // Input is an _unordered_ collection of categories, where "id", "parentId", and "name" are pre-populated.
        // Implement this method to print the full path for each category in the collection.
        //
        // For example, if category A is parent of category B and category B is parent of category C, then
        //      the path for category A is "A"
        //      the path for category B is "A > B"
        //      the path for category C is "A > B > C"
        //  where "A" is the name of category A
        //        "B" is the name of category B
        //        "C" is the name of category C
        //
        // Note: Number of categories in a specific path can be greater than 3 as provided in this example.
        //       Your solution should work with any number of parents (e.g. A > B > C > D > ... > X)

        StringBuilder result = new StringBuilder();
        Map<String, Category> categoriesHelper = new HashMap();

        categories.forEach((category) -> categoriesHelper.put(category.getId(), category));

        categories.forEach((category) -> {
            result.append("the path for category ");
            result.append(category.getName());
            result.append(" is \"");
            result.append(getCategoryPath(category, categoriesHelper));
            result.append(category.getName());
            result.append("\"");
            result.append(System.lineSeparator());
        });

        System.out.println(result);
        return result.toString();
    }

    public static String getCategoryPath(Category category, Map<String, Category> categoriesHelper) {
        if (category == null || category.getParentId() == null) {
            return "";
        }
        String parentName = "";
        Category parentCategory = categoriesHelper.get(category.getParentId());
        if (parentCategory == null) {
            parentName = "NOT EXIST";
        } else {
            parentName = parentCategory.getName();
        }
        return getCategoryPath(categoriesHelper.get(category.getParentId()), categoriesHelper) + parentName + " > ";
    }

    public static void main(String... args) {
        // Define a collection of Category instances
        // Invoke "printPath" method above to print the path for all the categories in the collection

        List<Exercise.Category> categories;
        String idCategoryA = UUID.randomUUID().toString();
        String idCategoryB = UUID.randomUUID().toString();
        String idCategoryC = UUID.randomUUID().toString();
        Exercise.Category categoryA = new Exercise.Category(idCategoryA, null, "A");
        Exercise.Category categoryB = new Exercise.Category(idCategoryB, idCategoryA, "B");
        Exercise.Category categoryC = new Exercise.Category(idCategoryC, idCategoryB, "C");
        categories = new ArrayList<>();
        categories.add(categoryA);
        categories.add(categoryB);
        categories.add(categoryC);
        Exercise.printPath(categories);
    }
}