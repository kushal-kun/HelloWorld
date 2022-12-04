import java.util.*;
public class simultaneous {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("number of variables");
        int n = sc.nextInt();
        double matrix[][] = new double[n][n+1];
        ArrayList<Double> values = new ArrayList<Double>();
        populate(matrix,n);
        printmatrix(matrix, n);
        convert(matrix, n);
        System.out.println();
        System.out.println();
        printmatrix(matrix, n);
        calculate(matrix, n, values);
        System.out.println();
        System.out.println();
        for(int i=0;i<n;i++){
            values.set(i, ((double)Math.round(values.get(i))));
        }
        System.out.println(values);
    }
    public static void populate(double matrix[][],int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<=n;j++){
                matrix[i][j]=sc.nextInt();
            }
        }
    }
    public static void printmatrix(double matrix[][],int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<=n;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void convert(double matrix[][],int n){
        int current = 0;
        for(int i=0;i<n-1;i++){
            for(int k=i+1;k<n;k++){
                double t = matrix[k][current];
                for(int j=current;j<=n;j++){
                    matrix[k][j]=((double)matrix[k][j])/t;
                    matrix[k][j]=matrix[k][j]*matrix[i][current];
                    matrix[k][j]=matrix[k][j]-matrix[i][j];
                }
            }
            current++;
        }
        current=0;
        for(int i=0;i<n;i++){
            double t = matrix[i][current];
            for (int j=0;j<=n;j++){
                matrix[i][j]=((double)matrix[i][j])/t;
            }
            current++;
        }
    }
    public static void calculate(double matrix[][],int n,ArrayList<Double> values){
        int current=n-1;
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(j<=current){
                    System.out.println("break");
                    break;
                }
                matrix[i][n]-=matrix[i][j];
                System.out.println(matrix[i][n]);
            }
            values.add(0,matrix[i][n]);
            for(int k=n-1;k>=0;k--){
                matrix[k][current]*=matrix[i][n];
            }
            current--;
        }
    }
}
