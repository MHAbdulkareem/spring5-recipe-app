package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Easy Peasy Guacamole");
        recipe1.setCookTime(5);
        recipe1.setPrepTime(5);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setServings(4);
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe1.setSource("Simply Recipes");
        recipe1.setDirections("Be careful handling chili's if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");

        //Ingredients
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount(BigDecimal.valueOf(2));
        ingredient1.setDescription("Avocado");
        ingredient1.setRecipe(recipe1);
        ingredient1.setUom(unitOfMeasureRepository.findByDescription("Ripe").get());

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount(BigDecimal.valueOf(0.5));
        ingredient2.setDescription("Kosher Salt");
        ingredient2.setRecipe(recipe1);
        ingredient2.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setAmount(BigDecimal.valueOf(1));
        ingredient3.setDescription("Lemon Juice");
        ingredient3.setRecipe(recipe1);
        ingredient3.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        //Categories
        Set<Category> categories1 = new HashSet<>();
        categories1.add(categoryRepository.findByDescription("Mexican").get());

        //Notes
        Notes notes = new Notes();
        notes.setRecipeNotes("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon \\n\" +\n" +
                "                \"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\\n\" +\n" +
                "                \"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. \\n\" +\n" +
                "                \"Add the chopped onion, cilantro, black pepper, and chili's. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness. \\n\" +\n" +
                "                \"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\\n\" +\n" +
                "                \"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) \\n\" +\n" +
                "                \"Refrigerate until ready to serve. Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Variations\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados. Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). \\n\" +\n" +
                "                \"Try guacamole with added pineapple, mango, or strawberries\\n\" +\n" +
                "                \"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\\n\" +\n" +
                "                \"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        //notes.setRecipe(recipe1);

        recipe1.setCategories(categories1);
        recipe1.setIngredients(ingredients);
        recipe1.setNotes(notes);

        //Load Recipe 2
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Grilled Cilantro Lime Chicken");
        recipe2.setCookTime(10);
        recipe2.setPrepTime(35);
        recipe2.setDifficulty(Difficulty.KIND_OF_HARD);
        recipe2.setServings(6);
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/grilled_cilantro_lime_chicken/");
        recipe2.setSource("Simply Recipes");
        recipe2.setDirections("Hello summer and cooking chicken on a grill! I don’t know about you, but sometimes I struggle with grilling chicken breasts.");

        //Ingredients
        Set<Ingredient> ingredients2 = new HashSet<>();

        recipe2.addIngredient(new Ingredient("Skinless, boneless chicken breasts", new BigDecimal(2), unitOfMeasureRepository.findByDescription("Pound").get()));
        recipe2.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        recipe2.addIngredient(new Ingredient("Chopped cilantro", new BigDecimal(3), unitOfMeasureRepository.findByDescription("Tablespoon").get()));

        //Categories
        Set<Category> categories1b = new HashSet<>();
        recipe2.getCategories().add(categoryRepository.findByDescription("American").get());

        //Notes
        Notes notes2 = new Notes();
        notes2.setRecipeNotes("1 Pound chicken breasts to even thickness: Place the chicken breasts between two piece of plastic wrap or wax paper and pound to an even thickness with a meat mallet or rolling pin.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"2 Marinate the chicken: Mix the olive oil, lime zest, lime juice, cilantro, sugar, salt and pepper together in a large bowl. Add the chicken and massage the marinade into the chicken. Cover and chill for at least 30 minutes, and up to 4 hours or overnight.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3 Grill: Preheat the grill for medium high heat. Remove the chicken breasts from the refrigerator. Remove them from the marinade and pat them dry with paper towels. Coat the chicken breasts with some olive oil. Soak a paper towel in a little more oil and use tongs to wipe the grill grates. When the grill is hot, place the chicken breasts on the grill. Grill for a few minutes on each side, until cooked through.");

        //notes2.setRecipe(recipe2);

        recipe1.setCategories(categories1);
        recipe1.setIngredients(ingredients);
        recipe1.setNotes(notes);

        recipe2.setCategories(categories1b);
        recipe2.setIngredients(ingredients2);
        recipe2.setNotes(notes2);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
    }

}
