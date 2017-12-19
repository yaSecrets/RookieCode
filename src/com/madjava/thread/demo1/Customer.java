package com.madjava.thread.demo1;

/**消费者
 * @author mding
 *    2017年12月19日
 * Content:
 */
public class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        super();
        this.depot = depot;
    }
    //消费产品：新建一个线程从仓库中消费产品
    public void Consume(final int val){
        new Thread(){
            public void run(){
                depot.consume(val);
            }
        }.start();
    }

    public static void main(String[] args) {
        Depot depot = new Depot(100);
        Producer mProducer = new Producer(depot);
        Customer mCustomer = new Customer(depot);

        mProducer.produce(60);
        mProducer.produce(100);
        mCustomer.Consume(50);
        mCustomer.Consume(200);
    }
}
