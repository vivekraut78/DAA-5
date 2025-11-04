public class practical5_part1 {
    
    static int[][] dp;
    static String x, y;
    
    static void computeLCS(String s1, String s2) {
        x = s1; y = s2;
        int m = s1.length(), n = s2.length();
        dp = new int[m + 1][n + 1];
        
        for(int i = 1; i <= m; i++)
            for(int j = 1; j <= n; j++)
                dp[i][j] = s1.charAt(i-1) == s2.charAt(j-1) ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
    }
    
    static String getLCS() {
        StringBuilder sb = new StringBuilder();
        int i = x.length(), j = y.length();
        while(i > 0 && j > 0) {
            if(x.charAt(i-1) == y.charAt(j-1)) {
                sb.insert(0, x.charAt(i-1));
                i--; j--;
            } else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.toString();
    }
    
    static void printMatrix() {
        int rows = Math.min(dp.length, 5), cols = Math.min(dp[0].length, 8);
        System.out.print("    ");
        for(int j = 0; j < cols; j++) System.out.print(y.charAt(j) + " ");
        System.out.println("...");
        for(int i = 0; i < rows; i++) {
            System.out.print(i == 0 ? "  " : x.charAt(i-1) + " ");
            for(int j = 0; j < cols; j++) System.out.print(dp[i][j] + " ");
            System.out.println("...");
        }
    }
    
    static String findLRS(String s) {
        computeLCS(s, s);
        String lcs = getLCS();
        StringBuilder lrs = new StringBuilder();
        int px = 0, py = 0;
        for(char c : lcs.toCharArray()) {
            int p1 = s.indexOf(c, px);
            int p2 = s.indexOf(c, py);
            if(p1 != p2) {
                lrs.append(c);
                px = p1 + 1;
                py = p2 + 1;
            }
        }
        return lrs.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("===== TASK-1: LCS FOR DNA SEQUENCES =====\n");
        
        String x1 = "AGCCCTAAGGGCTACCTAGCTT";
        String y1 = "GACAGCCTACAAGCGTTAGCTTG";
        
        System.out.println("X: " + x1);
        System.out.println("Y: " + y1);
        computeLCS(x1, y1);
        System.out.println("\nMatrix:");
        printMatrix();
        System.out.println("LCS: " + getLCS() + " | Length: " + dp[x1.length()][y1.length()]);
        
        System.out.println("\n===== TASK-2: LONGEST REPEATING SUBSEQUENCE =====\n");
        String s = "AABCBDC";
        System.out.println("String: " + s);
        System.out.println("LRS: " + findLRS(s));
    }
}