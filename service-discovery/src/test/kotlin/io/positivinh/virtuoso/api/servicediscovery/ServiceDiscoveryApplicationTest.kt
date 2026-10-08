package io.positivinh.virtuoso.api.servicediscovery

import io.micrometer.tracing.Tracer
import io.positivinh.virtuoso.observability.observation.IgnoredPathsObservationPredicate
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class ServiceDiscoveryApplicationTest {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Test
    fun contextLoads() {

        Assertions.assertThat(applicationContext.getBean(Tracer::class.java)).isNotNull
        // Eureka's own /eureka/** traffic (registrations, heartbeats) is not traced
        Assertions.assertThat(applicationContext.getBean(IgnoredPathsObservationPredicate::class.java)).isNotNull
    }
}
