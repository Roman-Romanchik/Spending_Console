package org.aren_rend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.util.LinkedList;

public class SelectionScreen {
    private static final JsonNode rootNode;

    static {
        try (InputStream inputStream = SelectionScreen.class.getClassLoader().getResourceAsStream("basic\\strings.xml")) {
            if (inputStream == null) {
                throw new RuntimeException("file data.xml not found resources");
            }
            XmlMapper mapper = new XmlMapper();
            rootNode = mapper.readTree(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error in getStringXML: " + e.getMessage(), e);
        }
    }

    public String getGreeting() {
        return rootNode.get("greeting").asText();
    }

    public void getMainMenu() {
        LinkedList<String> mainMenu = new LinkedList<>() {{
            add(rootNode.get("MainMenu").get("title").asText());
            add("1. " + rootNode.get("mainMenu").get("newNote").asText());
            add("2. " + rootNode.get("mainMenu").get("history").asText());
            add("3. " + rootNode.get("mainMenu").get("exit").asText());
        }};
        for(String point : mainMenu) {
            System.out.println(point);
        }
    }

    public void getHistoryMenu() {
        LinkedList<String> historyMenu = new LinkedList<>() {{
            add(rootNode.get("historyMenu").get("title").asText());
            add("1. " + rootNode.get("historyMenu").get("editNote").asText());
            add("2. " + rootNode.get("historyMenu").get("calculateSpends").asText());
            add("3. " + rootNode.get("historyMenu").get("return").asText());
        }};
        for(String point : historyMenu) {
            System.out.println(point);
        }
    }
}
