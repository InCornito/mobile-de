package de.mobile.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import de.mobile.domain.ad.Ad;
import de.mobile.domain.ad.AdType;
import de.mobile.domain.ad.Category;
import de.mobile.domain.customer.Customer;
import de.mobile.domain.customer.CustomerRole;
import de.mobile.domain.customer.CustomerType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@ChangeLog
public class MongodbMigration {

    private static final String MIGRATION_USER = "migrationUser";

    @ChangeSet(order = "001", id = "addAdminUser", author = MIGRATION_USER)
    public void addAdminUser(final MongoTemplate db) {
        if (db.exists(Query.query(Criteria.where("email").is("aaa@xxx.zzz")), Customer.class)) {
            return;
        }

        Customer customer = new Customer();
        customer.setFirstName("adminname");
        customer.setLastName("adminsurname");
        customer.setEmail("aaa@xxx.zzz");
        customer.setCompanyName("admincompany");
        customer.setCustomerType(CustomerType.WEB);
        customer.setCustomerRole(CustomerRole.ADMIN);
        customer.setPassword("$2a$10$wbB8nXnrFWfNF0i3CwvDMuycOnpgMsT5VJKajDqycds2JNfjDrria");
        db.save(customer);
    }

    @ChangeSet(order = "002", id = "addTestAds", author = MIGRATION_USER)
    public void addTestAds(final MongoTemplate db) {
        IntStream.rangeClosed(1, 6).forEach(value -> {
            Ad ad = new Ad();
            ad.setMake("Make " + value);
            ad.setCustomerEmail("aaa@xxx.zzz");
            ad.setCategory(Category.CAR);
            ad.setDescription("description");
            ad.setModel("Model" + value);
            ad.setPrice(BigDecimal.valueOf(77777.77));
            ad.setAdType(AdType.MOBILE);
            db.save(ad);
        });
    }
}
