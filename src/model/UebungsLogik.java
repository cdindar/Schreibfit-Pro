package model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UebungsLogik {
    private List<Wort> alleWoerter = new ArrayList<>();
    private Map<String, Integer> sessionStats = new HashMap<>();
    private List<Double> historyProzent = new ArrayList<>();

    public UebungsLogik() {
        sessionStats.put("Leicht", 0);
        sessionStats.put("Mittel", 0);
        sessionStats.put("Schwer", 0);
    }

    public void ladeAusDatei(String pfad) {
        alleWoerter.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.isBlank()) continue;
                String[] t = line.split(";");
                if (t.length >= 6) {
                    alleWoerter.add(new Wort(t[0].trim(), t[1].trim(), t[2].trim(), t[3].trim(), t[4].trim(), t[5].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    public List<Wort> erstelleSession(String kat, String stufe) {
        List<Wort> gefiltert = alleWoerter.stream()
                .filter(w -> w.kategorie.equalsIgnoreCase(kat) && w.stufe.equalsIgnoreCase(stufe))
                .collect(Collectors.toList());

        Collections.shuffle(gefiltert);
        List<Wort> session = gefiltert.stream().limit(10).collect(Collectors.toList());

        session.forEach(w -> w.userKorrekt = true);

        sessionStats.put(stufe, sessionStats.get(stufe) + 1);
        return session;
    }

    public void registriereErgebnis(double prozent) {
        historyProzent.add(prozent);
    }

    public String getStatistikInfo() {
        double avg = historyProzent.isEmpty() ? 0 : historyProzent.stream().mapToDouble(d -> d).average().orElse(0);
        return String.format("Sessions: L: %d | M: %d | S: %d  —  Schnitt: %.1f%%",
                sessionStats.get("Leicht"), sessionStats.get("Mittel"), sessionStats.get("Schwer"), avg);
    }
}