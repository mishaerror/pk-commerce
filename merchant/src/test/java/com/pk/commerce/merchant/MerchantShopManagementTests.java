package com.pk.commerce.merchant;

import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import com.pk.commerce.merchant.exception.EmailNotUniqueException;
import com.pk.commerce.merchant.exception.NameNotUniqueException;
import com.pk.commerce.merchant.exception.ShopNameNotUniqueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MerchantShopManagementTests {

    @Autowired
    private MerchantRegistrationService merchantRegistrationService;

    //merchant shop management scenarios
    @Test
    void addMerchantRegistrationRequest() {
        merchantRegistrationService.addMerchantRegistrationRequest("TopShop", "TopShopDeluxe",
                "Michael Martens", "mikmar@v.at", "+19203-02", "ByStrasse 443");

        assertThrows(NameNotUniqueException.class, () ->
                merchantRegistrationService.addMerchantRegistrationRequest("TopShop", "TopShopDeluxe2",
                        "Michael Martens", "mikmar2@v.at", "+19203-02", "ByStrasse 443")
        );


        assertThrows(ShopNameNotUniqueException.class, () ->
                merchantRegistrationService.addMerchantRegistrationRequest("TopShop2", "TopShopDeluxe",
                        "Michael Martens", "mikmar2@v.at", "+19203-02", "ByStrasse 443")
        );

        assertThrows(EmailNotUniqueException.class, () ->
                merchantRegistrationService.addMerchantRegistrationRequest("TopShop2", "TopShopDeluxe2",
                        "Michael Martens", "mikmar@v.at", "+19203-02", "ByStrasse 443")
        );
    }

    @Test
    void merchantRegistrationApproved() {

    }

    @Test
    void merchantRegistrationRejected() {

    }

    @Test
    void merchantViewsMerchantDetails() {

    }

    @Test
    void merchantEditMerchantDetails() {

    }

    @Test
    void merchantDeletesShop() {

    }

}
