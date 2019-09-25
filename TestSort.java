package TestSort;

import Heap.TestHeap;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

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
       ts.bsInsertSort(array);
        System.out.println(Arrays.toString(array));
       // ts.testTime();

    }
}
