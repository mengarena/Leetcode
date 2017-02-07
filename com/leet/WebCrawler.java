import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class MyWC {
	public static void main(String[] args) {
		List<String> urls;
		
		urls.add("http://www.yahoo.com");
		urls.add("http://newyorktimes.com");
		
		int maxUrlCnt = 1000;
		
		WebCrawler wc = new WebCrawler(urls, maxUrlCnt);
		
		CrawlThread ct = new CrawlThread(wc);
		
		for (int i=1; i<=10; i++) {
			new Thread(ct, "Thread-" + i).start();
		}
		
		while (true) {
		    if (wc.isFinished()) {
			    ct.shutdown();
			    
			    break;
			}
			
			try {
			   Thread.sleep(1000);
			} catch (Exception e) {
			   ;
			}
		}
		
		Set<String> visitedURL = wc.getCrawledURL();
		
		return;
    }
    
}


class CrawlThread implements Runnable {
    private WebCrawlwer wc;
    private volatile boolean bStop;
    
    public CrawlThread(WebCrawler wc) {
	    this.wc = wc;
	    
	    bStop = false;
	}
	
	public void run() {
		
	    while (!bStop) {
			try {
				if (wc.isFinished()) break;
				
			    String curUrl = wc.getURL();
			    			    
			    List<String> nextUrls = new ArrayList<>();
		        Set<String> tmpWords = new HashSet<String>();
		    
		        wc.crawlPage(curUrl, nextUrls, tmpWords);
			    
		        for (String nextUrl:nextUrls) wc.addURL(nextUrl);
		        
		        wc.mergeWords(curUrl, tmpWords);
		        wc.addVisitedURL(curUrl);
						    
		    } catch (InterruptedException e) {
			
			}
		}
	}
	
	public void shutdown() {
	    bStop = true;
	}
}

class WebCrawler {
    private Set<String> urlVisited;
    private Queue<String> urlToVisit;
    private int maxUrlCnt;
    private Map<String, Set<String>> pageWords;  //Store parsed words;  Word, Set<URL>
    
    public WebCrawler(List<String> urls, int maxUrlCnt) {
	    urlToVisit = new LinkedList<String>(urls);
	    
	    urlVisited = new HashSet<String>();
	    
	    this.maxUrlCnt = maxUrlCnt;
	    
	    pageWords = new HashMap<String, Set<String>>();
	}
	
    public WebCrawler(List<String> urls) {
	    urlToVisit = new LinkedList<String>(urls);
	    
	    urlVisited = new HashSet<String>();
	    
	    this.maxUrlCnt = 0;
	    
	    pageWords = new HashMap<String, Set<String>>();
	}	
	
	//BFS
	public void crawl_singleThread() {
	    
	    while (!urlToVisit.isEmpty() && urlVisited.size() <= maxUrlCnt) {
		    String curUrl = urlToVisit.poll();
		    		    
		    List<String> nextUrls = new ArrayList<>();
		    Set<String> tmpWords = new HashSet<String>();
		    
		    crawlPage(curUrl, nextUrls, tmpWords);
		    
		    for (String nextUrl:nextUrls) {
			    if (!urlVisited.contains(nextUrl)) urlToVisit.offer(nextUrl);
			}   
			
			mergeWords(curUrl, tmpWords);
			
			urlVisited.add(curUrl);
		}    
	}
		
	//////////////////	
	public synchronized String getURL() throws InterruptedException {
		//if (urlVisited.size() >= maxUrlCnt) return "";
		
	    while (urlToVisit.size() == 0) wait();
	    
	    String sUrl = urlToVisit.poll():
	    
	    return sUrl;
	}
	
	public synchronized void addURL(String nextURL) {
		//if (urlVisited.size() >= maxUrlCnt) return;
	    if (urlVisited.contains(nextURL)) return;
	    
	    boolean bEmpty = false;
	    
	    if (urlToVisit.size() == 0) bEmpty = true;
	    
	    urlToVisit.offer(nextURL);
	    
	    if (bEmpty) notifyAll();
	}
	
	public synchronized void addVisitedURL(String visitedURL) {
	    if (urlVisited.contains(visitedURL)) return;
	    
	    urlVisited.add(visitedURL);
	}
	////////////////////
	
	
	public Set<String> getCrawledURL() {
	    return new HashSet<String>(urlVisited);
	}
	
	public Map<String, Set<String>> getPageWords() {
	    return new HashMap<String, Set<String>>(pageWords);
	}
	
	public synchronized boolean isFinished() {
	    if (urlVisited.size() >= maxUrlCnt) return true;
	    
	    return false;
	}
	
	//Parse curUrl, store the found urls on this page into nextUrls, store the words on this page into words
	private void crawlPage(String curUrl, List<String> nextUrls, Set<String> words) {
	    
	}
	
	//Merge the url-words (on one page) into pageWords
	private void mergeWords(String curUrl, HashSet<String> tmpWords) {
	    for (String word:tmpWords) {
			
		    if (pageWords.containsKey(word)) {
			    pageWords.get(word).add(curUrl);
			} else {
				Set<String> urls = new HashSet<String>();
				urls.add(curUrl);
				pageWords.put(word, urls);
			}
		}
	}
	
}
