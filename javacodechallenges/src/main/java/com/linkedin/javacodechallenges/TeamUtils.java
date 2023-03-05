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

    // check if there is no scores
    for (Team team: teams) {
      if (team.sumTotalScore() > 0) {
        ;
      } else {
        System.out.println("The game hasn't started yet.");
        return;
      }
    }

    for (Team team: teams) {
      results.put(team, team.sumTotalScore());
    }
    orderedResults.putAll(results);

    System.out.println("Now for the results, the WINNER is...");
      boolean firstResult = true;
      for (Team team: orderedResults.keySet()){
        if (firstResult) {
          System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!\n");
          firstResult = false;
        } else {
          System.out.println("Then we have... ");
          System.out.println("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!\n");
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