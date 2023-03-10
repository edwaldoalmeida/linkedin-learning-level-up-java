package com.linkedin.javacodechallenges;

import java.util.*;

public class TeamUtils {

  public static void generateTeamsScores(List<Team> teams, int numberOfRounds) {
    Random random = new Random();
    teams.forEach(team -> {
      for (int i = 0; i < numberOfRounds; i++) {
        team.getScores().add(random.nextInt(11));
      }
    });
  }

  public static void revealResults(List<Team> teams) {
    Map<Team, Integer> results = new HashMap<>();
    Map<Team, Integer> orderedResults = new TreeMap<>(new ValueComparator(results));

    if (teams.isEmpty()) {
      System.out.println("The game hasn't started yet.");
      return;
    }

    for (Team team: teams) {
      if (team.sumTotalScore() > 0) {
        results.put(team, team.sumTotalScore());
      }
    }

    if (results.isEmpty()) {
      System.out.println("The game hasn't started yet.");
    } else {
      orderedResults.putAll(results);
      System.out.println("Now for the results, the WINNER is...");
      Iterator<Map.Entry<Team, Integer>> iterator = orderedResults.entrySet().iterator();
      Map.Entry<Team, Integer> currentEntry = null;
      Map.Entry<Team, Integer> nextEntry = null;
      currentEntry = iterator.next();  // gets the 1st position
      nextEntry = iterator.next(); // gets the 2nd position

      // if it's a TIE: print `current` and `next` (then current = iterator.next() and `next` = iterator.next())
      while (currentEntry != null) {
        if (nextEntry != null && currentEntry.getKey().sumTotalScore() == nextEntry.getKey().sumTotalScore()) {
          System.out.println("It's a TIE!");
          System.out.println("With " + currentEntry.getKey().sumTotalScore() + " points, it's team " +
                  currentEntry.getKey().getPlayer1() + " and " +
                  currentEntry.getKey().getPlayer2() + "!");
          System.out.println("With " + nextEntry.getKey().sumTotalScore() + " points, it's team " +
                  nextEntry.getKey().getPlayer1() + " and " +
                  nextEntry.getKey().getPlayer2() + "!\n");
          if (iterator.hasNext()) {
            currentEntry = iterator.next();
          } else {
            currentEntry = null;
          }
          if (iterator.hasNext()) {
            nextEntry = iterator.next();
          } else {
            nextEntry = null;
          }
        } else { // if it's not a TIE: print only the current (then current = next and next = iterator.next()
          System.out.println("With " + currentEntry.getKey().sumTotalScore() + " points, it's team " +
                  currentEntry.getKey().getPlayer1() + " and " +
                  currentEntry.getKey().getPlayer2() + "!\n");
          currentEntry = nextEntry;  // gest the 2nd position from the `nextEntry`
          if (iterator.hasNext()) {
            nextEntry = iterator.next();  // gets the 3rd position
          } else {
            nextEntry = null;
          }
        }
        if (currentEntry != null) {
          System.out.println("Then we have... ");
        }
      }
    }
  }

  static class ValueComparator implements Comparator<Team> {
    Map<Team, Integer> map;

    public ValueComparator(Map<Team, Integer> map) {
      this.map = map;
    }

    @Override
    public int compare(Team a, Team b) {
//      if (map.get(a) >= map.get(b)) {
      if (map.get(a) <= map.get(b)) {
        return 1;
      } else {
        return -1;
      }
    }
  }


}