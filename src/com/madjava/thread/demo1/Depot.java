package com.madjava.thread.demo1;

public class Depot {
    private int capacity;// 仓库的容量
    private int size;// 仓库的实际数量

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public synchronized void product(int val) {
        try {
            // left表示想要生产的数量
            int left = val;
            while (left > 0) {
                //库存已满时，等待消费者消费产品
                while (size >= capacity)    wait();
                //获取实际生产的数量（即库存中 新增的数量）
                //实际数量+想要生产的数量>容量，则实际增量=容量-实际数量，否则实际增量=想要生产的数量
                int inc = (size+left) > capacity? (capacity-size):left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left = %3d, inc = %3d,size = %3d\n", Thread.currentThread().getName(),val,left,inc,size);
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void consume(int val){
        try {
            //left表示客户要消费的数量（有可能消费量太大，库存不够，需多次消费）
            int left = val;
            while(left > 0){
                //库存为0时，等待生产者生产产品
                while(size <= 0)    wait();
                //获取实际消费数量（即库存中实际减少的数量）
                //如果库存<客户要消费的数量，则实际消费量=库存，否则实际消费量=想要消费的数量
                int dec  = (size<left)?size:left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                notifyAll();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }

    public String toString(){
        return "capacity:"+capacity+", actual size:"+size;
    }
}