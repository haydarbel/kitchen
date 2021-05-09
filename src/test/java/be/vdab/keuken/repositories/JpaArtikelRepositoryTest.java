package be.vdab.keuken.repositories;

import be.vdab.keuken.domain.Artikel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BIG_DECIMAL;

@DataJpaTest
@Sql("/InsertArtikel.sql")
@Import(JpaArtikelRepository.class)
class JpaArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaArtikelRepository repository;
    private static final String ARTIKELS="artikels";
    private Artikel artikel;

    JpaArtikelRepositoryTest(JpaArtikelRepository repository) {
        this.repository = repository;
    }
    @BeforeEach
    void beforeEach() {
        artikel = new Artikel("testART", BigDecimal.TEN, BigDecimal.valueOf(15));
    }

    private long idVanTestArtikel() {
        return jdbcTemplate.queryForObject("select id from artikels where naam='testA'",Long.class);
    }
    @Test
    void findArtikelById() {
        assertThat(repository.findArtikelById(idVanTestArtikel()).get().getNaam()).isEqualTo("testA");
    }

    @Test
    void findOnbestandeId() {
        assertThat(repository.findArtikelById(-1)).isNotPresent();
    }

    @Test
    void create() {
        repository.create(artikel);
        assertThat(artikel.getId()).isPositive();
        assertThat(countRowsInTableWhere(ARTIKELS, "id=" + artikel.getId())).isOne();
    }
}