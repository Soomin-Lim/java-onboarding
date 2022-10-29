package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Problem7 {

    static class Friend implements Comparable<Friend> {
        String name;
        int score;

        public Friend(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Friend o) {
            if (this.score == o.score)
                return this.name.compareTo(o.name);
            return o.score - this.score;
        }
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = new ArrayList<>();

        HashMap<String, List<String>> friendMap = new HashMap<>();

        for (List<String> list : friends) {
            makeFriendMap(friendMap, list);
        }

        List<String> userFriend = friendMap.get(user);
        HashMap<String, Integer> friendScore = new HashMap<>();

        for (String name : friendMap.keySet()) {
            if (name.equals(user) || userFriend.contains(name))
                continue;

            List<String> findFriend = friendMap.get(name);
            int score = getScore(userFriend, findFriend);

            if (score > 0)
                friendScore.put(name, score);
        }

        for (String name : visitors) {
            if (!userFriend.contains(name))
                friendScore.put(name, friendScore.getOrDefault(name, 0) + 1);
        }

        List<Friend> friendList = new ArrayList<>();
        for (String name : friendScore.keySet()) {
            System.out.println(name + " " + friendScore.get(name));
            friendList.add(new Friend(name, friendScore.get(name)));
        }

        findMaxFiveFriends(friendList, answer);

        return answer;
    }

    public static void makeFriendMap(HashMap<String, List<String>> friendMap, List<String> list) {
        String a = list.get(0);
        String b = list.get(1);

        if (friendMap.containsKey(a)) {
            friendMap.get(a).add(b);
        }
        else {
            List<String> newList = new ArrayList<>();
            newList.add(b);
            friendMap.put(a, newList);
        }

        if (friendMap.containsKey(b)) {
            friendMap.get(b).add(a);
        }
        else {
            List<String> newList = new ArrayList<>();
            newList.add(a);
            friendMap.put(b, newList);
        }
    }

    public static int getScore(List<String> userFriend, List<String> findFriend) {
        int cnt = 0;

        for (String name : userFriend) {
            if (findFriend.contains(name))
                cnt++;
        }

        return cnt;
    }

    public static void findMaxFiveFriends(List<Friend> friendList, List<String> answer) {
        Collections.sort(friendList);

        for (int i = 0; i < friendList.size(); i++) {
            if (i == 5) break;

            answer.add(friendList.get(i).name);
        }
    }
}
