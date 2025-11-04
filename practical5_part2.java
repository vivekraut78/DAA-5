public class practical5_part2{
    
    static int[][] dp;
    
    static void computeLCS(String s1, String s2, boolean isLRS) {
        int m = s1.length(), n = s2.length();
        dp = new int[m + 1][n + 1];
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1) && (!isLRS || i != j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }
    
    static String backtrack(String s1, String s2, boolean isLRS) {
        StringBuilder sb = new StringBuilder();
        int i = s1.length(), j = s2.length();
        
        while(i > 0 && j > 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1) && (!isLRS || i != j)) {
                sb.insert(0, s1.charAt(i-1));
                i--; j--;
            } else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.toString();
    }
    
    static void printFullMatrix(String s1, String s2) {
        System.out.print("       ");
        for(int j = 0; j < s2.length(); j++) 
            System.out.printf("%2c ", s2.charAt(j));
        System.out.println();
        
        for(int i = 0; i <= s1.length(); i++) {
            if(i == 0) System.out.print("     ");
            else System.out.printf("%2c   ", s1.charAt(i-1));
            
            for(int j = 0; j <= s2.length(); j++) 
                System.out.printf("%2d ", dp[i][j]);
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // TASK 1
        System.out.println("===== TASK-1: LCS FOR DNA SEQUENCES =====\n");
        String x = "AGCCCTAAGGGCTACCTAGCTT";
        String y = "GACAGCCTACAAGCGTTAGCTTG";
        
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        
        computeLCS(x, y, false);
        System.out.println("\nComplete Cost Matrix:");
        printFullMatrix(x, y);
        
        String lcs = backtrack(x, y, false);
        System.out.println("\nLCS Length: " + dp[x.length()][y.length()]);
        System.out.println("LCS: " + lcs);
        
        // TASK 2
        System.out.println("\n\n===== TASK-2: LONGEST REPEATING SUBSEQUENCE =====\n");
        String s = "AABCBDC";
        System.out.println("String: " + s);
        
        computeLCS(s, s, true);
        System.out.println("\nComplete Cost Matrix:");
        printFullMatrix(s, s);
        
        String lrs = backtrack(s, s, true);
        System.out.println("\nLRS Length: " + dp[s.length()][s.length()]);
        System.out.println("LRS: " + lrs);
    }
}