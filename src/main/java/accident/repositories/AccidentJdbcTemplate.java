package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate implements Store {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident create(Accident accident, String[] ids) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    @Override
    public void update(Accident accident) {
        jdbc.update("update accident set name = ? where id = ?",
                accident.getName(),
                accident.getId()
        );
    }

    @Override
    public Accident get(int id) {
        return jdbc.queryForObject("select id, name from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                }, id);
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query("select id, name from accident order by id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> getAllAccidentType() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Collection<Rule> getAllRules() {
        return jdbc.query("select id, name from accident_rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}