package pl.inzynier.netintegrator.loadbalancer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoadBalancerServiceFactory {

    static LoadBalancerService create(DatabaseConfiguration databaseConfiguration) {
        return new LoadBalancerServiceImpl();
    }

}
