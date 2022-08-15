import java.util.*;
class simultaneous{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n,i,j;
        try{
            System.out.println("enter size of system");
            n = sc.nextInt();
            double x[] = new double[n];
            double b[] = new double[n];
            double a[][] = new double[n][n];
            System.out.println("enter coefficients rowwise");
            for(i=0;i<n;i++){
                System.out.println("row:"+(i+1));
                for(j=0;j<n;j++){
                    a[i][j]=sc.nextDouble();
                }
            }
            System.out.println("enter vector b");
            for(j=0;j<n;j++){
                b[j]=sc.nextDouble();
            }
            int index[] = new int[n];
            x=gaussian(a,b,index);
            System.out.println("solution vector:");
            for(i=0;i<n;i++){
                System.out.println(x[i]+" ");
            }
        }catch(Exception e){
            System.out.println("exception occured");
        }
    }
    public static double[] gaussian(double a[][],double b[],int index[]){
        int n = b.length;
        double x[] = new double[n];
        gaussian(a,index);
        for(int i=0;i<n-1;++i){
            for(int j=i+1;j<n;++j){
                b[index[j]]-=a[index[j]][i]*b[index[i]];
            }
        }
        x[n-1]=b[index[n-1]]/a[index[n-1]][n-1];
        for(int i=n-2;i>=0;--i){
            x[i]=b[index[i]];
            for(int j=1+i;j<n;++j){
                x[i]/=a[index[i]][i];
            }
        }
        order(x,index);
        return x;
    }
    public static void gaussian(double a[][],int index[]){
        int n=index.length;
        double c[] = new double[n];
        for(int i=0;i<n;++i){
            index[i]=i;
        }
        for(int i=0;i<n;++i){
            double c1=0;
            for(int j=0;j<n;++j){
                double c0=Math.abs(a[i][j]);
                if(c0>c1){
                    c1=c0;
                }
                c[i]=c1;
            }
        }
        int k=0;
        for(int j=0;j<n-1;++j){
            double pi1 = 0;
            for(int i=j;i<n;++i){
                double pi0=Math.abs(a[index[i]][j]);
                pi0/=c[index[i]];
                if(pi0>pi1){
                    pi1=pi0;
                    k=i;
                }
            }
            int itmp = index[j];
            index[j]=index[k];
            index[k]=itmp;
            for(int i=j+1;i<n;++i){
                double pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j]=pj;
                for(int l=j+1;l<n;++l){
                    a[index[i]][l]-=pj*a[index[j]][l];
                }
            }
        }
    }
    public static void order(double x[],int index[]){
        int m=x.length;
        for(int i=0;i<m;++i){
            for(int j=i+1;j<m;++j){
                if(index[i]>index[j]){
                    int itmp=index[i];
                    index[i]=index[j];
                    index[j]=itmp;
                    double xtmp = x[i];
                    x[i]=x[j];
                    x[j]=xtmp;
                }
            }
        }
    }
}