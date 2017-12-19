package com.madjava.thread.demo1;

/**生产者
 * @author mding
 *    2017年12月19日
 * Content:
 */
public class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        super();
        this.depot = depot;
    }
    // 新建一个线程向仓库中生产产品
    public void produce(final int val){
        new Thread(){
            public void run(){
                depot.product(val);
            }
        }.start();
    }
}