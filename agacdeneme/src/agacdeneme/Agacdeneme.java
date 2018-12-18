
package agacdeneme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
 
public class Agacdeneme {
    private static class Node {
        String sub = "";                   
        List<Integer> ch = new ArrayList<>();  // cocuk nodelar listesi
    }
 
    
    
    private static class SuffixTree {
        private List<Node> dugum = new ArrayList<>();
 
        public SuffixTree(String str) {
            dugum.add(new Node());
            for (int i = 0; i < str.length(); ++i) {
                suffixekle(str.substring(i));
            }
        }
 
        private void suffixekle(String suf) {
            int n = 0;
            int i = 0;
            while (i < suf.length()) {
                char b = suf.charAt(i);
                List<Integer> cocuk_dugum = dugum.get(n).ch;
                int x2 = 0;
                int n2;
                while (true) {
                    if (x2 == cocuk_dugum.size()) {
                       
                        n2 = dugum.size();
                        Node temp = new Node();
                        temp.sub = suf.substring(i);
                        dugum.add(temp);
                        cocuk_dugum.add(n2);
                        return;
                    }
                    n2 = cocuk_dugum.get(x2);
                    if (dugum.get(n2).sub.charAt(0) == b) break;
                    x2++;
                }
                
                String sub2 = dugum.get(n2).sub;
                int j = 0;
                while (j < sub2.length()) {
                    if (suf.charAt(i + j) != sub2.charAt(j)) {
                        
                        int n3 = n2;
                       
                        n2 = dugum.size();
                        Node temp = new Node();
                        temp.sub = sub2.substring(0, j);
                        temp.ch.add(n3);
                        dugum.add(temp);
                        dugum.get(n3).sub = sub2.substring(j);  
                        dugum.get(n).ch.set(x2, n2);
                        break;  
                    }
                    j++;
                }
                i += j;  
                n = n2;  
            }
        }
 
        public void goster() {
            if (dugum.isEmpty()) {
                System.out.println("bos");
                return;
            }
            goster_f(0, "");
        }
 
        private void goster_f(int n, String pre) {
            List<Integer> cocuk_dugum = dugum.get(n).ch;
            if (cocuk_dugum.isEmpty()) {
                System.out.println("- " + dugum.get(n).sub);
                return;
            }
            System.out.println("┐ " + dugum.get(n).sub);
            for (int i = 0; i < cocuk_dugum.size() - 1; i++) {
                Integer c = cocuk_dugum.get(i);
                System.out.print(pre + "├─");
                goster_f(c, pre + "│ ");
            }
            System.out.print(pre + "└─");
            goster_f(cocuk_dugum.get(cocuk_dugum.size() - 1), pre + "  ");
        }
    }
     
    public static void main(String[] args) {
        String metin_s1;
        String metin_s2;
        Scanner input = new Scanner(System.in);
        System.out.println("------------Suffix string giriniz------------ :");
         metin_s1 = input.nextLine();       
          System.out.println("\n");
         
        System.out.println("-----Birinci Asama----- :"+"\n");
        new SuffixTree(metin_s1).goster();
        System.out.println("\n");
        
        //////////////////////////////////////////////////////2
  
     System.out.println("-----İkinci Asama----- :"+"\n");
    // S2 stringinin s1  içinde bulunup bulunmama durumu
      System.out.println("S1 stringi içinde aranacak S2 stringini giriniz :");
      metin_s2 = input.nextLine();
          
  if(metin_s1.contains(metin_s2)== true)
  {     System.out.println( metin_s2+" "+"stringi "+metin_s1+" "+"içinde BULUNUR.");
  }
  else if(metin_s1.contains(metin_s2)== false)
  { System.out.println( metin_s2 +" "+"stringi "+metin_s1 +" "+"içinde BULUNMAZ."); 
 
  }
     System.out.println("\n");
    ////////////////////////////////////////////////////////3
    
    System.out.println("-----Ücüncü Asama----- :"+"\n");
    
    System.out.println("Tekrar eden en uzun alt dizilim : "+lrs(metin_s1));
    System.out.println("\n");
    
    //////////////////////////////////////////////////////4
    
     System.out.println("-----Dördüncü Asama----- :"+"\n");
    
  /// en cok tekrar eden alt dizilim
           
               encokharfbul(metin_s1);
  
  //// en çok tekrar eden en uzun alt dizilim
//        System.out.println("En Çok Tekrar Eden En Uzun Altdizilim : ");
//        
//        System.out.println(longestRepeatedSubstring(metin_s1)+"\n");
        
    ///////////////////////////////////////////////////////4
     
        
        System.out.println("-----------------------------------------");
  
  
  
    }
    
    
    
    public static void encokharfbul(String kelime){ 
        
     int sayi=0; int encokkactane=0; int index = 0;
    for (int i = 0; i < kelime.length(); i++) {
        sayi=0;
    for (int j = 0; j < kelime.length(); j++)
    { if(kelime.charAt(i)==kelime.charAt(j)) sayi++; } 
    if(sayi>encokkactane) 
    { encokkactane = sayi; index = i; }   } 
    
    System.out.println("En çok tekrar eden alt dizilim:"+" "+kelime.charAt(index)); 
    System.out.println("Tekrar sayısı:"+" "+encokkactane);  
   
    ///////////////////////////
    
    String alfabe = "abcdefghijklmnopqrstuvwxyz$"; 
    int[] count = new int[alfabe.length()];     //alfabenin uzunluğu kadar bir sayac dizisi tanımladım.
    for (int k = 0; k < kelime.length(); k++) {         
        int indexim = alfabe.indexOf(kelime.charAt(k));    //indexim değişkenine kelimedeki k nin bulunduğu indisteki değerini verdik.
        if (indexim < 0)                                   // bu harfin kacıncı değerden itibaren başladığını öğrenip tekrarında artırdık.
            continue;     count[indexim]++;     }  
    
    for (int z = 0; z < count.length; z++) {  
            if (count[z] < 1)   continue;  
    System.out.println(String.format("%s (%d) ", alfabe.charAt(z),count[z] ));   
        

       
    
        }
       
  
    
    }
  
   
// static String longestRepeatedSubstring(String str) { 
//        int n = str.length(); 
//        int LCSRe[][] = new int[n + 1][n + 1]; 
//  
//        String res = ""; 
//        int res_length = 0;   
//  
//       
//        int i, index = 0; 
//        for (i = 1; i <= n; i++) { 
//            for (int j = i + 1; j <= n; j++) { 
//                  
//                
//                if (str.charAt(i - 1) == str.charAt(j - 1) 
//                        && LCSRe[i - 1][j - 1] < (j - i)) { 
//                    LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1; 
//  
//                    
//                    if (LCSRe[i][j] > res_length) { 
//                        res_length = LCSRe[i][j]; 
//                        index = Math.max(i, index); 
//                    } 
//                } else { 
//                    LCSRe[i][j] = 0; 
//                } 
//            } 
//        } 
//  
//        if (res_length > 0) { 
//            for (i = index - res_length + 1; i <= index; i++) { 
//                res += str.charAt(i - 1); 
//            } 
//        } 
//  
//        return res; 
//    } 


 ////////
 //////// Tekrar eden en uzun alt dizilim
 

    public static String lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);  // eger birbirinin içindeyse kontrolü
        }
        return s.substring(0, n);  //eger kapsıyorsa en uzun substring odur
    }


    
    public static String lrs(String s) {
                                            // stringi substringlere parçalarız.
        
        int n  = s.length();
        String[] suffixler = new String[n];
        for (int i = 0; i < n; i++) {
            suffixler[i] = s.substring(i, n);
        }
                                           // eklediğimiz dizide sıralarız.
        
        Arrays.sort(suffixler);

        //her suffixden sonraki suffixle kıyaslayıp kısa suffixi alıp sayısını n değerine atarız. devam yukarıda
        String lrs = "";
        for (int i = 0; i < n-1; i++) {
            String x = lcp(suffixler[i], suffixler[i+1]);
            if (x.length() > lrs.length())
                lrs = x;
        }
        return lrs;
    }
 
    
    
 //////////////////
}

