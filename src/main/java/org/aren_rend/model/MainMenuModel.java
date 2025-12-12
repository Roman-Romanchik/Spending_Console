package org.aren_rend.model;

import javafx.collections.ObservableList;
import org.aren_rend.data.SaveData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuModel {
	private static final Path filePath = Paths.get("/home/aren_rend/Programs/1_Intellij_Idea/Projects/Spending_Console/SaveInformation.txt");
	private int count = 0;
	List<String> rangedNotes;

	public String changeButtonText() {
		count++;
		if(count % 2 == 1) {
			return "Add next!";
		} else {
			return "More!";
		}
	}

	public String makeNote(String category, String spendingName, String spendingPrice) {
		StringBuilder note = new StringBuilder();
		note.append(". ")
				.append(category)
				.append(" : ")
				.append(spendingName)
				.append(" : ")
				.append(spendingPrice)
				.append(" rub");
		return SaveData.save(note);
	}

	public String getAllSpending(ObservableList<String> notes) {
		return calculateSpending(notes, "All: ");
	}

	public String getMonthSpending(ObservableList<String> notes) {

		LocalDate now = LocalDate.now();
		Pattern amountPattern = Pattern.compile(":\\s*(\\d+)\\s*rub");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		int amount = 0;
		boolean isCurrentMonth = false;

		for (String line : notes) {
			line = line.trim();

			if (line.startsWith("---") && line.endsWith("---")) {
				String dateStr = line.substring(3, line.length() - 3);
				try {
					LocalDate date = LocalDate.parse(dateStr, dateFormatter);
					isCurrentMonth = (date.getYear() == now.getYear() &&
							date.getMonthValue() == now.getMonthValue());
				} catch (Exception e) {
					isCurrentMonth = false;
				}
			}
			else if (isCurrentMonth && line.matches("\\d+\\.\\s+.*")) {
				var matcher = amountPattern.matcher(line);
				if (matcher.find()) {
					amount += Integer.parseInt(matcher.group(1));
				}
			}
		}

		return "Month: " + amount;
	}

	public String getWeekSpending(ObservableList<String> notes) {

		return calculateSpending(rangedNotes, "Week: ");
	}

	private String calculateSpending(List<String> notes, String label) {
		Pattern pattern = Pattern.compile(":(\\s*)(\\d+)(\\s*)rub");
		int amount = 0;
		for(String note : notes) {
			Matcher matcher = pattern.matcher(note);
			if(matcher.find()) {
				amount += Integer.parseInt(matcher.group(2));
			}
		}
		return label + amount;
	}

}
