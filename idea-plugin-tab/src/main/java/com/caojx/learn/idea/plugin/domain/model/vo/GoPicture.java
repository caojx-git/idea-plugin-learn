package com.caojx.learn.idea.plugin.domain.model.vo;

/**
 * http://web.juhe.cn:8080/finance/stock/hs?gid=sz000651&key=4bc5772802902af54f95e5570bbf0595
 * 股票数据，响应数据
 * {
 *     "resultcode": "200",
 *     "reason": "SUCCESSED!",
 *     "result": [
 *         {
 *             "data": {
 *                 "buyFive": "300",
 *                 "buyFivePri": "30.770",
 *                 "buyFour": "1300",
 *                 "buyFourPri": "30.780",
 *                 "buyOne": "6100",
 *                 "buyOnePri": "30.840",
 *                 "buyThree": "1700",
 *                 "buyThreePri": "30.800",
 *                 "buyTwo": "2600",
 *                 "buyTwoPri": "30.830",
 *                 "competitivePri": "30.840",
 *                 "date": "2022-03-16",
 *                 "gid": "sz000651",
 *                 "increPer": "-1.15",
 *                 "increase": "-0.36",
 *                 "name": "格力电器",
 *                 "nowPri": "30.840",
 *                 "reservePri": "30.850",
 *                 "sellFive": "5000",
 *                 "sellFivePri": "30.900",
 *                 "sellFour": "1700",
 *                 "sellFourPri": "30.890",
 *                 "sellOne": "12300",
 *                 "sellOnePri": "30.850",
 *                 "sellThree": "9500",
 *                 "sellThreePri": "30.880",
 *                 "sellTwo": "2100",
 *                 "sellTwoPri": "30.860",
 *                 "time": "10:24:39",
 *                 "todayMax": "31.700",
 *                 "todayMin": "30.730",
 *                 "todayStartPri": "31.610",
 *                 "traAmount": "634022806.860",
 *                 "traNumber": "203120",
 *                 "yestodEndPri": "31.200"
 *             },
 *             "dapandata": {
 *                 "dot": "30.84",
 *                 "name": "格力电器",
 *                 "nowPic": "-0.36",
 *                 "rate": "-1.15",
 *                 "traAmount": "63444",
 *                 "traNumber": "203257"
 *             },
 *             "gopicture": {
 *                 "minurl": "http://image.sinajs.cn/newchart/min/n/sz000651.gif",
 *                 "dayurl": "http://image.sinajs.cn/newchart/daily/n/sz000651.gif",
 *                 "weekurl": "http://image.sinajs.cn/newchart/weekly/n/sz000651.gif",
 *                 "monthurl": "http://image.sinajs.cn/newchart/monthly/n/sz000651.gif"
 *             }
 *         }
 *     ],
 *     "error_code": 0
 * }
 */
public class GoPicture {

    /**
     * 分钟k线url
     */
    private String minurl;

    /**
     * 日k url
     */
    private String dayurl;

    /**
     * 周k url
     */
    private String weekurl;

    /**
     * 月k url
     */
    private String monthurl;

    public String getMinurl() {
        return minurl;
    }

    public void setMinurl(String minurl) {
        this.minurl = minurl;
    }

    public String getDayurl() {
        return dayurl;
    }

    public void setDayurl(String dayurl) {
        this.dayurl = dayurl;
    }

    public String getWeekurl() {
        return weekurl;
    }

    public void setWeekurl(String weekurl) {
        this.weekurl = weekurl;
    }

    public String getMonthurl() {
        return monthurl;
    }

    public void setMonthurl(String monthurl) {
        this.monthurl = monthurl;
    }
}
