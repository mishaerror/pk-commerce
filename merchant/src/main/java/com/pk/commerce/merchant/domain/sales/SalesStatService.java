package com.pk.commerce.merchant.domain.sales;

import org.springframework.stereotype.Service;

@Service
public class SalesStatService {
    public void salesReport(Integer days) {
        var s = """
                {
                    "summary": {
                      "totalRevenue": 0,
                      "totalOrders": 0,
                      "averageOrderValue": 0,
                      "topSellingItems": 0
                    },
                    "dailySales": [],
                    "ordersByStatus": [],
                    "topItems": [],
                    "recentOrders": []
                  }""";

        var sqlSummary = "";
    }
}
