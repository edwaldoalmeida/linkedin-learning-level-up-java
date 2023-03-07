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

      // check the winner(s) and print the results in order
//      boolean firstResult = true;
//      for (Team team: orderedResults.keySet()){
//        if (firstResult) {
//          System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!\n");
//          firstResult = false;
//        } else {
//          System.out.println("Then we have... ");
//          System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!\n");
//        }
//      }

      // check the winners (new approach)
      Iterator<Map.Entry<Team, Integer>> iterator = orderedResults.entrySet().iterator();
      // TODO: include a while i < something.lenght() - 2 ???
      int i = 0;
      Map.Entry<Team, Integer> currentEntry = null;
      Map.Entry<Team, Integer> nextEntry = null;
      while (i <= orderedResults.size()) {
        if (iterator.hasNext()) {
          currentEntry = iterator.next();
          if (iterator.hasNext()) {
            nextEntry = iterator.next();
            // if it's a TIE, print the current and next together:
            if (currentEntry.getKey().sumTotalScore() == nextEntry.getKey().sumTotalScore()) {
              System.out.println("It's a TIE!");
              System.out.println("With " + currentEntry.getKey().sumTotalScore() + " points, it's team " +
                      currentEntry.getKey().getPlayer1() + " and " +
                      currentEntry.getKey().getPlayer2() + "!");
              System.out.println("With " + nextEntry.getKey().sumTotalScore() + " points, it's team " +
                      nextEntry.getKey().getPlayer1() + " and " +
                      nextEntry.getKey().getPlayer2() + "!\n");
            } else { // print only the current and let the execution go
              System.out.println("With " + currentEntry.getKey().sumTotalScore() + " points, it's team " +
                      currentEntry.getKey().getPlayer1() + " and " +
                      currentEntry.getKey().getPlayer2() + "!\n");
              System.out.println("Then we have... ");
              System.out.println("With " + nextEntry.getKey().sumTotalScore() + " points, it's team " +
                      nextEntry.getKey().getPlayer1() + " and " +
                      nextEntry.getKey().getPlayer2() + "!\n");
            }
            System.out.println("Then we have... ");
          } else {
            System.out.println("With " + currentEntry.getKey().sumTotalScore() + " points, it's team " +
                    currentEntry.getKey().getPlayer1() + " and " +
                    currentEntry.getKey().getPlayer2() + "!\n");
          }
        }
        i += 1;
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