package com.pk.commerce.merchant.rest.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/sales")
public class SalesController {
    @GetMapping("/summary")
    public ResponseEntity<?> summary(@RequestParam(name = "days", defaultValue = "7") Integer days) {
        return ResponseEntity.ok("""
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
                  }""");
    }
}
