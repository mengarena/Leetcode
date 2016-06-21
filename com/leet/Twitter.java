package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. 
//Your design should support the following methods:
//
//postTweet(userId, tweetId): Compose a new tweet.
//getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
//Each item in the news feed must be posted by users who the user followed or by the user herself. 
//Tweets must be ordered from most recent to least recent.
//
//follow(followerId, followeeId): Follower follows a followee.
//unfollow(followerId, followeeId): Follower unfollows a followee.
//
//Example:
//
//Twitter twitter = new Twitter();
//
//// User 1 posts a new tweet (id = 5).
//twitter.postTweet(1, 5);
//
//// User 1's news feed should return a list with 1 tweet id -> [5].
//twitter.getNewsFeed(1);
//
//// User 1 follows user 2.
//twitter.follow(1, 2);
//
//// User 2 posts a new tweet (id = 6).
//twitter.postTweet(2, 6);
//
//// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
//// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
//twitter.getNewsFeed(1);
//
//// User 1 unfollows user 2.
//twitter.unfollow(1, 2);
//
//// User 1's news feed should return a list with 1 tweet id -> [5],
//// since user 1 is no longer following user 2.
//twitter.getNewsFeed(1);


public class Twitter {
    private List<Integer> lstTweetId = null;       //Overall Tweets
    private List<Integer> lstTweetUserId = null;   //User Id corresponding to the Tweet in lstTweetId

    private Map<Integer, Set<Integer>> hmFollow = null;   //The users one guy follows (including himself)
    
    /** Initialize your data structure here. */
    public Twitter() {
        lstTweetUserId = new ArrayList<Integer>();
        lstTweetId = new ArrayList<Integer>();
        hmFollow = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        lstTweetUserId.add(userId);
        lstTweetId.add(tweetId);
        
        if (!hmFollow.containsKey(userId)) {
            Set<Integer> setFollowee = new HashSet<Integer>();
            setFollowee.add(userId);
            hmFollow.put(userId, setFollowee);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> lstNews = new ArrayList<Integer>();
        if (!hmFollow.containsKey(userId) || lstTweetId.isEmpty()) return lstNews;
        
        Set<Integer> setFollowee = hmFollow.get(userId);
        
        int i = lstTweetId.size()-1;
        int cnt = 0;
        int TweetUserId;
        
        while (i >= 0 && cnt < 10) {
            TweetUserId = lstTweetUserId.get(i);
            
            if (setFollowee.contains(TweetUserId)) {
                lstNews.add(lstTweetId.get(i));
                cnt++;
            }
            
            i--;
        }
        
        return lstNews;
    }
    
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!hmFollow.containsKey(followerId)) {
            Set<Integer> setFollowee = new HashSet<Integer>();
            setFollowee.add(followerId);
            setFollowee.add(followeeId);
            hmFollow.put(followerId, setFollowee);
        } else {
            hmFollow.get(followerId).add(followeeId);
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!hmFollow.containsKey(followerId) || followerId == followeeId) return;   //Self is not allowed to unfollow
        if (hmFollow.get(followerId).contains(followeeId)) hmFollow.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */