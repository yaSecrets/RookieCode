package com.madjava.stack;

/**
 *

 Given n points on a 2D plane, find the maximum number of points that lie on the same straight line. 
 */
public class MaxPoints {
    public int maxPoints(Point[] points) {
        //定义三个点A、B、C
        int ABx = 0;
        int ABy = 0;
        int BCx = 0;
        int BCy = 0;
        if(points.length <= 2) return points.length;
        int max = 2;//在一条线上的点的最大个数
        for(int i = 0;i < points.length;i++){
            int num = 1;
            int dup = 0;//重复的点
            for(int j = i+1;j < points.length;j++){
                ABx = points[i].x - points[j].x;//AB两点横坐标之差
                ABy = points[i].y - points[j].y;//AB两点纵坐标之差
                if(ABx == 0 && ABy == 0){//两点重合
                    dup++;
                }else{//两点不重合
                    num++;
                    for(int k = j+1;k < points.length;k++){
                        BCx = points[j].x - points[k].x;
                        BCy = points[j].y - points[k].y;
                        if(ABx * BCy == BCx * ABy){//斜率相等在一条直线上
                            num++;
                        }
                    }
                }
                max = max < num+dup ? num+dup : max;
                num = 1;
            }
        }
        return max;
    }
}
