package TestSort;




import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * @ClassName TestSort
 * @Description TODO
 * @Auther danni
 * @Date 2019/9/24 18:46]
 * @Version 1.0
 **/

public class TestSort {
    //插入排序
    public static void insertSort(int[] array){
        for (int i = 1;i <array.length ; i++) {
            int key=array[i];
            int j;
            for(j=i-1;j>=0&&array[j]>key;j--){//倒着找。减少时间复杂度
                array[j+1]=array[j];
            }
            array[j+1]=key;
        }
    }
    //折半插入排序（稳定性）
    public  static void bsInsertSort(int[] array){
        for (int i = 1; i <array.length ; i++) {
            int key=array[i];
            int left=0;
            int right=i;//区间[left,right)
            while(left<right){
                int mid=(left+right)/2;
                if(key>=array[mid]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            for (int j = i; j>left ; j--) {
                array[j] = array[j - 1];
            }
            array[left]=key;
        }
    }

    //希尔排序
    public static void shellSort(int[] array){
       int gap=array.length;
       while(true){
           gap=(gap/3)+1;
           for (int i = gap; i <array.length ; i++) {
               int k;
               int temp=array[i];
               for (k = i-gap; k>=0&&array[k]>temp ; k-=gap) {
                   array[k+gap]=array[k];
               }
               array[k+gap]=temp;
           }
           if(gap==1){
               break;
           }
       }
    }
    //选择排序(找每次循环中最小数)
    public static void selectSort(int[] array){
        for (int i = 0; i <array.length-1; i++) {
            int minindex=i;
            int j;
            for (j = i+1; j <array.length; j++) {
                if(array[j]<array[minindex]){
                    minindex=j;
                }
            }
            swamp(array,minindex,i);
        }
    }
    //选择排序(找每次循环中最大数)
    public static void selectSort1(int[] array){
        for (int i = 0; i <array.length-1; i++) {
            int maxindex=array.length-1-i;
            int j;
            for (j = 0; j <array.length-i; j++) {
                if(array[j]>array[maxindex]){
                    maxindex=j;
                }
            }
            swamp(array,maxindex,array.length-i-1);
        }
    }
    //双向选择排序
    public static void doubleSelectSort(int[] array){
        int left=0;
        int right=array.length-1;
        while(left<right){
            int max=left;
            int min=left;
            for (int i = left+1; i <=right ; i++) {
                if(array[i]<=array[min]){
                    min=i;
                }else if(array[i]>array[max]){
                    max=i;
                }
            }
            swamp(array,left,min);
            if(max==0){
                max=min;
            }
            swamp(array,right,max);
            left++;
            right--;
        }
    }
    //堆排序（大堆）
    public static void heapSort(int[] array){
       createHeapBig1(array,array.length);
        for (int i = 0; i < array.length-1 ; i++) {
            swamp(array,0,array.length-1-i);
           shiftDownBig1(array,0,array.length-1-i);
        }
    }
    private static void shiftDownBig1(int[] array, int i, int size) {
        int left=(i*2)+1;
        while(left<size){
            int right=left+1;
            int max=left;
            if(right<size){
                if(array[left]<array[right]){
                    max=right;
                }
            }
            if(array[i]<array[max]){
                swamp(array,i,max);
                i=max;
                left=(i*2)+1;
            } else{
                break;
             }
        }
    }
    private static void createHeapBig1(int[] array, int length) {
        for (int i = (length-2)/2; i >=0 ; i--) {
            shiftDownBig1(array,i,length);
        }
    }
    //冒泡排序
    public static void bubbleSort(int[] array){
        boolean flag=false;
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = 0; j <array.length-i-1 ; j++) {
                if(array[j]>array[j+1]){
                    swamp(array,j,j+1);
                    flag=true;
                }
            }
            if(!flag){
               break;
            }
        }
    }
    //快速排序-递归
    public static void quickSort(int[] array,int left,int right){
            if(left>=right){
                return;
            }
           int mid= partion4(array,left,right);
           quickSort(array,left,mid-1);
           quickSort(array,mid+1,right);
    }
    //快速排序-非递归
    public static void quickSort1(int[] array){
        Stack<Integer> stack=new Stack<>();
        stack.push(array.length-1);
        stack.push(0);
        while(!stack.empty()){
            int left=stack.pop();
            int right=stack.pop();
            if(left>right){
                continue;
            }
            int mid=partion(array,left,right);
            stack.push(right);
            stack.push(mid+1);

            stack.push(mid-1);
            stack.push(left);
        }
    }
    //hoare
    private static int partion(int[] array,int left,int right){
        int i=left;
        int j=right;
        int key=array[left];
        while(j>i){
            while(array[j]>=key&&i<j){
                j--;
            }
            while(array[i]<=key&&i<j){
                i++;
            }
            swamp(array,i,j);
        }
        swamp(array,i,left);
        return i;
    }
    //挖坑
    private static int partion2(int[] array,int left,int right){
        int i=left;
        int j=right;
        int key=array[left];
        while(j>i){
            while(array[j]>=key&&i<j){
                j--;
            }
            array[i]=array[j];
            while(array[i]<=key&&i<j){
                i++;
            }
           array[j]=array[i];
        }
       array[i]=key;
        return i;
    }
    //前后遍历
    private static int partion3(int[] array,int left,int right){
        int d=left+1;
        int key=array[left];
        int i=0;
        for (i = left+1; i <array.length ; i++) {
              if(array[i]<key){
                swamp(array,i,d);
                d++;
             }
        }
        swamp(array,left,d-1);
        return d-1;
    }
    //优化版
    private static int partion4(int[] array,int left,int right){
        int i=left;
        int j=right;
        int key=array[left];
        int k=left;
        while(k<=j){
            if(array[k]==key){
                i++;
            }
            if(array[k]<key){
                swamp(array,k,i);
                i++;
                k++;
            }else if(array[k]>key){
                swamp(array,k,j);
                j--;
            }
            if(k>j){
                break;
            }
        }
        return j;
    }
    //归并排序-递归
    public static void mergeSort(int[] array){
        mergeInsertSort(array,0,array.length);//【low,high)
    }
    public static void mergeInsertSort(int[] array,int low,int high){
        if(low>=high-1){
            return;
        }
        int mid=(low+high)/2;
        mergeInsertSort(array,low,mid);
        mergeInsertSort(array,mid,high);
        merge(array,low,mid,high);
    }

    //归并排序-迭代
    public static void mergeSort1(int[] array){
        for (int i = 1; i <array.length ; i*=2) {
            for (int j = 0; j <array.length ; j+=i*2) {
                int low=j;
                int mid=low+i;
                if(mid>=array.length){
                    continue;
                }
                int high=mid+i;
                if(high>=array.length){
                    high=array.length;
                }
                merge(array,low,mid,high);
            }
        }
    }
    public static void merge(int[] array,int low,int mid,int high ){
        int len=high-low;
        int[] arr=new int[len];
        int i=low;
        int j=mid;
        int k=0;
        while(i<mid&&j<high&&k<arr.length){
            if(array[i]<=array[j]){
                arr[k++]=array[i++];
            }else {
                arr[k++] = array[j++];
            }
        }
        while(i<mid){
            arr[k++]=array[i++];
        }
        while(j<high){
            arr[k++]=array[j++];
        }
       System.arraycopy(arr,0,array,low,len);
    }

    public static void swamp(int[] array, int j, int i) {
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
    public static void testTime(){
       int[] arr=new int[10*10000];
        Random random=new Random(2019-9-24);
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=random.nextInt(10*1000);
        }
        double before=System.nanoTime();
        heapSort(arr);
        double after=System.nanoTime();
        double ms=(after-before)/1000/1000;
        System.out.printf("共耗时%.5f毫秒",ms);
    }
    public static void main(String[] args) {
        int[] array={4,5,1,7,6,9,2,8,3,9};
        TestSort ts=new TestSort();
       /*ts.insertSort(array);
        System.out.println(Arrays.toString(array));*/
        /*ts.shellSort(array);
        System.out.println(Arrays.toString(array));*/
        /*ts.bubbleSort(array);
        System.out.println(Arrays.toString(array));*/
       /* ts.heapSort(array);
        System.out.println(Arrays.toString(array));*/
       /*ts.doubleSelectSort(array);
        System.out.println(Arrays.toString(array));*/
        /*ts.selectSort(array);
        System.out.println(Arrays.toString(array));*/
       /*ts.selectSort1(array);
        System.out.println(Arrays.toString(array));*/
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
       // ts.testTime();

    }
}
