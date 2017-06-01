package cz.cizek.edu.trans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * @author jiricizek <jiri.cizek@cleverlance.com>
 */
@Service
public class JdbcService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void increment() {
        List<Integer> result = jdbcTemplate.queryForList("select c from counter", Integer.class);
        Integer counter = result.get(0);

        System.out.println("Counter je: " + counter);
        counter += 1;

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Novy counter je: " + counter);
        jdbcTemplate.update("update counter set c=" + counter);
    }

    @Transactional
    public void increment1() {
        List<Integer> result = jdbcTemplate.queryForList("select c from counter", Integer.class);
        Integer counter = result.get(0);

        System.out.println("Counter je: " + counter);
        counter += 1;

        System.out.println("Novy counter je: " + counter);
        jdbcTemplate.update("update counter set c=" + counter);
    }
}
