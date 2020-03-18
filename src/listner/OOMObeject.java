package listner;

import java.util.ArrayList;

/**
 * @Version 1.0
 * @Author:杭利达
 * @Date:2020/3/2
 * @Content:
 **/
public class OOMObeject {
    static class OOMObject{
        public byte[] placeHolder=new byte[64*1024];
    }
    public static void fillHeap(int num)throws InterruptedException{
        ArrayList<OOMObeject> list = new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            Thread.sleep(50);
            list.add(new OOMObeject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
