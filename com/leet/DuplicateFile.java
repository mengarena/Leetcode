import java.util.*;
import java.io.File;


//Steps:  Group 1) based on size  2) hash - MD5  3) Content
 
public class DuplicateFile {
    //BFS
    public static Set<String> getAllFiles(String sRoot) {
        Queue<String> folders = new LinkedList<String>();
        Set<String> files = new HashSet<String>();

        try {
            File f = new File(sRoot);
            if (!f.isDirectory()) return files;

            folders.offer(sRoot);

            while (!folders.isEmpty()) {
                String sFolder = folders.poll();
                System.out.println("Folder = " + sFolder);

                File fl = new File(sFolder);
                String[] fileFolders = fl.list();

                for (String fileFolder:fileFolders) {
                    String sFullpath = sFolder + File.separator + fileFolder;
                    File tmp = new File(sFullpath);

                    if (tmp.isFile() && tmp.canRead()) files.add(sFullpath);

                    if (tmp.isDirectory() && tmp.canRead()) folders.offer(sFullpath);
                }
            }

        } catch (Exception e) {
            System.out.println("This.....exception!");
        }

        return files;
    }
    
    

    private String md5(String sFilePath) {
	    return "";
	}
	
	
	//RandomAccessFile raf = new RandomAccessFile(string, "r");
	//RandomAccessFile raf = new RandomAccessFile(File, mode);
	//raf.seek(offset);
	//raf.read(byte[], int offset, int len)
	private boolean CompareFiles(String sFileA, String sFileB) {
	    RandomAccessFile rafA = new RandomAccessFile(sFileA, "r");
	    RandomAccessFile rafB = new RandomAccessFile(sFileB, "r");
	    
	    byte[] bA = new byte[size];
	    byte[] bB = new byte[size];
	    
	    int offset = 0;
	    int size = 1000;
	    
	    while (offset + size < rafA.length()) {
			int nA = rafA.read(bA, offset, size);
			int nB = rafB.read(bB, offset, size);
			
			if (nA != nB) return false;
			for (int i=0; i<size; i++) {
				if (bA[i] != bB[i]) return false;
			}
			
			offset += size;
	    }
		
		return true;
	}
	 
	//Group files based on content 
	private List<List<String>> getSubGroupsByContent(List<String> lstFiles) {
	    int i,j;
	    int n = lstFiles.size();
	    List<List<String>> lstlstSG = new ArrayList<List<String>>();
	    boolean[] visited = new boolean[lstFiles.size()];
	    
	    for (i=0; i<n; i++) {
		    if (visited[i]) continue;
		    
		    List<String> lstSG = new ArrayList<>();
		    
		    lstSG.add(lstFiles.get(i));
		    visited[i] = true;
		    
		    for(j=i+1; j<n; j++) {
			    if (visited[j]) continue;
			    
			    if (CompareFiles(lstFiles.get(i), lstFiles.get(j))) {
				    lstSG.add(lstFiles.get(j));
				}
			}
			
			if (lstSG.size() >= 2) {
			    lstlstSG.add(lstSG);
			}
		}
	    
	    
        return lstlstSG;
	}

    //Group files based on hash (MD5)
    private List<List<String>> getSubGroups(List<String> lstFiles) {
	    List<List<String> lstlstSG = new ArrayList<List<String>>();
	    Map<String, List<String>> mapSG = new HashMap<String, List<String>>();
	    
	    for (String sFile:lstFiles) {
		    String sMD5 = md5(sFile);
		    
		    if (mapSG.containsKey(sMD5)) {
			    mapSG.get(sMD5).add(sFile);
			} else {
				List<String> lstSG = new ArrayList<>();
				lstSG.add(sFile);
				mapSG.put(sMD5, lstSG);
			}
		}
	    
	    lstlstSG = new ArrayList<List<String>>(mapSG.values());
	    
	    //Further, Group files based on Content
	    for (int i=lstlstSG.size()-1; i>=0; i--) {			
			if (lstSG.size() < 2) {
			    lstlstSG.remove(i);
		    } else {
				List<String> lstSG = lstlstSG.remove(i);
			    List<List<String>> llSG = getSubGroupsByContent(lstSG);
			    
			    lstlstSG.addAll(i, llSG);
		    }
		}
	    
	    return lstlstSG;
	}

    //File fl = new File(sRoot);
    //File[]  lstFiles():  Return an array of pathnames denoting the files in the directory
    //String[] list():  Return an array of strings naming the files and directories in the directory 
    //size:   fl.length()
    //Readable:  fl.canRead()
    //Is folder:  fl.isDirectory()
    //Is file:    fl.isFile()
    
    //Steps:  Group 1) based on size  2) hash - MD5  3) Content
    public List<List<String>> getDuplicateFiles(String sRoot) {
	    Set<String> setFiles = getAllFiles(sRoot);
	    List<List<String>> groups = new ArrayList<List<String>>();
	    
	    if (setFiles.size() < 2) return groups;
	    
	    Map<Integer, List<String>> hm = new HashMap<Integer, List<String>>();
	    
	    //Group based on file size
	    for (String sFile:setFiles) {
		    File fl = new File(sFile);
		    int size = fl.length();
		    
		    if (hm.containsKey(size)) {
			    hm.get(size).add(sFile);
			} else {
			    List<String> lstFiles = new ArrayList<>();
			    lstFiles.add(sFile);
			    hm.put(size, lstFiles);
			}
		}
	    
	    //Group files based on hash content
	    groups = new ArrayList<List<String>>(hm.values());
	    
	    for (int i=groups.size()-1; i>=0; i++) {
					    
		    if (lstFiles.size() < 2) {
			    groups.remove(i);
			} else {
				List<String> lstFiles = groups.remove(i);
				
			    List<List<String>> lstlstSubGroups = getSubGroups(lstFiles);
				
			    for (List<String> lstSG:lstlstSubGroups) {
				    if (lstSG.size() >= 2) {
					    groups.add(i, lstSG);
					}
				}
			}
		}
	    
	    return groups;	    
	}

}

