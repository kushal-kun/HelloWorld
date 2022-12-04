import java.util.*;
public class simultaneousgen {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("enter number of variables(cannot be less than 3)");
        int n = sc.nextInt();
        if(n<3){
            System.out.println("error");
            return;
        }
        System.out.println("would you like to:\n1.Enter the variables\n2.generate with random variables");
        int c = sc.nextInt();
        int[] variables = new int[n];
        int[] valuesList = new int[(n*n)+n];
        if(c==1){
            for(int i=0;i<n;i++){
                variables[i]=sc.nextInt();
            }
        }else{
            int ll = 0;
            int ul = 100;
            System.out.println("enter the lower and upper limit of the variables, for default(0,100) enter -1");
            int in = sc.nextInt();
            if(in!=-1){
                ll=in;
                ul=sc.nextInt();
            }
            if((ul-ll<=n-1)){
                System.out.println("too many variables for this range");
                return;
            }
            for(int i=0;i<n;i++){
                int next = random(ll,ul);
                if(present(variables,next)>0){
                    i--;
                    continue;
                }else{
                    variables[i]=next;
                }
            }
        }
        generate(variables, n, valuesList);
        printList(valuesList);
        System.out.println("\n");
        printList(variables);
    }
    public static void generate(int[] variables,int n,int[] values){
        int next = 0,current = 1,counter = 0,sum = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                next = random(-100, 100);
                if(present(values, next)>(int)(n/2)||next==0){
                    j--;
                }else{
                    values[current-1]=next;
                    current++;
                }
            }
            counter=0;sum=0;
            for(int j=current-n-1;j<current-1;j++){
                sum+=values[j]*variables[counter];
                counter++;
            }
            values[current-1]=sum;
            current++;
        }
    }
    public static int random(int ll,int ul){
        return (int)(Math.random()*(ul-ll+1)+ll);
    }
    public static int present(int a[],int b){
        int temp=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==b){
                temp++;
            }
        }
        return temp;
    }
    public static void printList(int a[]){
        System.out.print("[");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println("\b]");
    }
}
