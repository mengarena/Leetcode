/*

535. Encode and Decode TinyURL

Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Medium:
Uber
*/

class Solution {

public:
    // 95%  ---  This is better solution
    unordered_map<string, string>  short2org;
    unordered_map<string, string>  org2short;
    string codebase="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    Solution() {
        srand(time(0));
    }
    
    // Encodes a URL to a shortened URL.
    string encode(string longUrl) {
        if (org2short.count(longUrl)) {
            return "http://tinyurl.com/" + org2short[longUrl];
        }
        
        string s = "";
        for (int i = 0; i<6; i++) s += codebase[rand() % 62];
        int idx = 0;
        
        while (short2org.count(s)) {
            s[idx] = codebase[rand() % 62];
            idx = (idx + 1) % 6;
        }
        
        short2org[s] = longUrl;
        org2short[longUrl] = s;
        return "http://tinyurl.com/" + s;
    }

    // Decodes a shortened URL to its original URL.
    string decode(string shortUrl) {
        string hashval = shortUrl.substr(shortUrl.find_last_of("/") + 1);
        return short2org.count(hashval) ? short2org[hashval]:shortUrl;
    }



    // 95%
    unordered_map<unsigned long long, string>  id_shorturl;
    unordered_map<unsigned long long, string>  id_orgurl;
    string codebase="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    unsigned long long id_base = 0;
    
    // Encodes a URL to a shortened URL.
    string encode(string longUrl) {
        id_base++;    // Not very good, same longUrl will have multiple entries
        string shorturl = getShortURL(id_base);
        id_shorturl[id_base] = shorturl;
        id_orgurl[id_base] = longUrl;
        return "http://tinyurl.com/" + shorturl;
    }

    // Decodes a shortened URL to its original URL.
    string decode(string shortUrl) {
        unsigned long long id = getId(shortUrl);
        if (id_orgurl.count(id) != 0) {
            return id_orgurl[id];
        } else {
            return shortUrl;
        }
    }
    
    string getShortURL(unsigned long long id) {
        string s = "";
        
        while (id != 0) {
            s += codebase[id % 62];
            id /= 62;
        }
        reverse(s.begin(), s.end());
        return s;
    }
    
    unsigned long long getId(string shorturl) {
        unsigned long long id = 0;
        string hashstr = shorturl.substr(shorturl.find_last_of("/") + 1);
        for (auto c:hashstr) {
            if (c >= '0' && c <= '9') {
                id = id*62 + (c - '0');
            } else if (c >= 'A' && c <= 'Z') {
                id = id*62 + (c - 'A' + 10);
            } else {
                id = id*62 + (c - 'a' + 36);
            }
        }
        return id;
    }
};

// Your Solution object will be instantiated and called as such:
// Solution solution;
// solution.decode(solution.encode(url));
