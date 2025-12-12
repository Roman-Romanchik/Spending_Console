package org.aren_rend.display;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SelectionScreen {
    private static final JsonNode rootNode;

    private static final List<String> mainMenu = new LinkedList<>();
    private static final List<String> historyMenu = new LinkedList<>();
    private static final List<String> definePeriodMenu = new LinkedList<>();
    private static final List<String> historyViewMenu = new LinkedList<>();
    private static final List<String> returnProcessMenu = new LinkedList<>();
    private static final List<String> categoriesMenu = new LinkedList<>();

    private static final String MENU = "menu";
    private static final String NEW_NOTE_PROCESS = "newNoteProcess";
    private static final String RETURN_PROCESS = "returnProcess";

    static {
        try (InputStream inputStream = SelectionScreen.class.getClassLoader()
                .getResourceAsStream("basic/strings.xml")) {
            if (inputStream == null) {
                throw new RuntimeException("File strings.xml not found in resources");
            }
            XmlMapper mapper = new XmlMapper();
            rootNode = mapper.readTree(inputStream);
            initializeMenus();
        } catch (Exception e) {
            throw new RuntimeException("Error loading strings.xml: " + e.getMessage(), e);
        }
    }

    private static void initializeMenus() {
        initializeMenu(mainMenu, "mainMenu", "menuTitle", "newNote", "history", "exit");

        initializeMenu(historyMenu, "historyMenu", "menuTitle", "definePeriod", "return");

        initializeMenu(definePeriodMenu, "definePeriodMenu", "menuTitle", "day", "week", "other", "return");

        initializeMenu(historyViewMenu, "historyViewMenu", "viewTitle", "editNote", "return");

        addMenuItems(returnProcessMenu,
                rootNode.get(RETURN_PROCESS).get("returnToHistoryMenu").asText(),
                rootNode.get(RETURN_PROCESS).get("returnToMainMenu").asText()
        );

        JsonNode categoriesNode = rootNode.get(NEW_NOTE_PROCESS).get("spendingCategories");
        addMenuItems(categoriesMenu,
                categoriesNode.get("title").asText(),
                categoriesNode.get("food").asText(),
                categoriesNode.get("sport").asText(),
                categoriesNode.get("clothes").asText(),
                categoriesNode.get("technique").asText(),
                categoriesNode.get("office").asText(),
                categoriesNode.get("furniture").asText(),
                categoriesNode.get("other").asText()
        );
    }

    private static void initializeMenu(List<String> menu, String menuKey, String... itemKeys) {
        JsonNode menuNode = rootNode.get(MENU).get(menuKey);

        for (String itemKey : itemKeys) {
            menu.add(menuNode.get(itemKey).asText());
        }
    }

    private static void addMenuItems(List<String> menu, String... items) {
        Collections.addAll(menu, items);
    }

    public String getGreeting() {
        return rootNode.get("greeting").asText();
    }

    public void displayMainMenu() {
        displayMenu(mainMenu);
    }

    public void displayHistoryMenu() {
        displayMenu(historyMenu);
    }

    public void displayDefinePeriodMenu() {
        displayMenu(definePeriodMenu);
    }

    public void displayHistoryViewMenu() {
        displayMenu(historyViewMenu);
    }

    public void displayReturnProcessMenu() {
        displayMenu(returnProcessMenu);
    }

    public void displayCategoriesMenu() {
        displayMenu(categoriesMenu);
    }

    private void displayMenu(List<String> menu) {
        for (String item : menu) {
            System.out.println(item);
        }

    }

    public String getSpendingName() {
        return getTextFromPath("newNoteProcess", "spendingName");
    }

    public String getSpendingCost() {
        return getTextFromPath("newNoteProcess", "spendingCost");
    }

    public String getCorrectSpelling() {
        return getTextFromPath(NEW_NOTE_PROCESS, "correctSpelling");
    }

    public String getVerification() {
        return getTextFromPath(NEW_NOTE_PROCESS, "verification");
    }

    private String getTextFromPath(String parent, String child) {
        return rootNode.get(parent).get(child).asText();
    }
}